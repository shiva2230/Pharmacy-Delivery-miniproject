package com.example.myapplication

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.database.CursorIndexOutOfBoundsException
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class PrescriptionActivity : AppCompatActivity() {
    private lateinit var previewImage: ImageView
    private lateinit var uploadButton: Button
    private lateinit var apiService: ApiService
    private lateinit var submitButton: Button

    private val PICK_IMAGE_REQUEST = 1
    private val READ_EXTERNAL_STORAGE_REQUEST_CODE = 1
    private val MEDIA_PERMISSIONS_REQUEST_CODE = 2
    private var selectedImageUri: android.net.Uri? = null

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            permissions.entries.forEach {
                val status = if (it.value) "ENABLED" else "DISABLED"
                Log.d("PermissionStatus", "${it.key}: $status")
            }

            // Continue with your logic after handling permissions
            // For example, you can check if READ_EXTERNAL_STORAGE is granted here
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                // Continue with your logic that requires READ_EXTERNAL_STORAGE
                // This part of the code will be executed if the permission is granted
                Log.d("PermissionStatus", "READ_EXTERNAL_STORAGE: ENABLED")
            }

            // Check if media permissions are granted here
            if (permissions[Manifest.permission.READ_MEDIA_IMAGES] == true &&
                permissions[Manifest.permission.READ_MEDIA_VIDEO] == true &&
                permissions[Manifest.permission.READ_MEDIA_AUDIO] == true
            ) {
                // Continue with your logic that requires media permissions
                Log.d("PermissionStatus", "Media permissions: ENABLED")
                uploadPrescription(selectedImageUri)
            } else {
                // Handle the case where not all media permissions are granted
                Log.d("PermissionStatus", "Media permissions: DISABLED")
            }
        }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        apiService = RetrofitClient.getRetrofitInstance().create(ApiService::class.java)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.prescription_activity)

        uploadButton = findViewById<Button>(R.id.button2)
        submitButton = findViewById<Button>(R.id.button3)
        previewImage = findViewById(R.id.previewImage)

        uploadButton.setOnClickListener {
            openImageBrowser()
        }

        submitButton.setOnClickListener {
            val intent = Intent(this@PrescriptionActivity, UploadSuccessActivity::class.java)
            requestPermissions()
            Log.d("success","submitted")
            startActivity(intent)
        }
    }

    private fun openImageBrowser() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        pickImageLauncher.launch(intent)
    }

    private val pickImageLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == AppCompatActivity.RESULT_OK) {
                // Properly set the selectedImageUri
                result.data?.data?.let {
                    selectedImageUri = it
                    Log.d("ImageSelection", "Selected Image URI: $selectedImageUri")
                    previewImage.setImageURI(selectedImageUri)
                    if (previewImage.getVisibility() == View.INVISIBLE) {
                        previewImage.setVisibility(View.VISIBLE);
                        submitButton.setVisibility(View.VISIBLE);
                    } else {
                        previewImage.setVisibility(View.INVISIBLE);
                        submitButton.setVisibility(View.INVISIBLE);
                    }
                } ?: run {
                    Log.d("ImageSelection", "No image selected")
                }
            }
        }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun requestPermissions() {
        val permissionsToRequest = mutableListOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_MEDIA_IMAGES,
            Manifest.permission.READ_MEDIA_VIDEO,
            Manifest.permission.READ_MEDIA_AUDIO
        )

        val permissionsNotGranted = permissionsToRequest.filter {
            ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
        }

        if (permissionsNotGranted.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                this,
                permissionsNotGranted.toTypedArray(),
                MEDIA_PERMISSIONS_REQUEST_CODE
            )
            uploadPrescription(selectedImageUri)
        } else {
            // All permissions are already granted
            uploadPrescription(selectedImageUri)
        }
    }

    private fun uploadPrescription(uri: android.net.Uri?) {
        Log.d("upload","uploadPrescription")
        selectedImageUri?.let { selectedUri ->
            val realPath = getRealPathFromURI(selectedUri)
            if (realPath.isNotBlank()) {
                val file = File(realPath)
                if (file.exists()) {
                    Log.d("Success", "File exists")
                    val requestFile =
                        RequestBody.create(MediaType.parse("multipart/form-data"), file)
                    val body = MultipartBody.Part.createFormData("file", file.name, requestFile)
                    Log.d("content",body.toString())
                    apiService.uploadImage(body)?.enqueue(object : Callback<String?> {
                        override fun onResponse(
                            call: Call<String?>,
                            response: Response<String?>
                        ) {
                            if (response.isSuccessful) {
                                val message = response.body()
                                Log.d("Success", "Pharmacy created successfully")
                                Log.d("message:", message.toString())
                            } else {
                                Log.d("Failure", "Empty")
                                Log.d("response:",response.body().toString())
                            }
                        }

                        override fun onFailure(call: Call<String?>, t: Throwable) {
                            Log.e("Failure", "Invalid")
                            Log.e("failure description", t.message.toString())
                        }
                    })
                } else {
                    Log.d("Failure", "File does not exist")
                }
            } else {
                Log.d("Failure", "Invalid file path")
            }
        } ?: run {
            Log.d("Failure", "No image selected")
        }
    }

    private fun getRealPathFromURI(uri: android.net.Uri): String {
        var path = ""
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor: Cursor? = contentResolver.query(uri, projection, null, null, null)

        try {
            cursor?.let {
                if (it.moveToFirst()) {
                    val columnIndex: Int = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                    path = it.getString(columnIndex)
                }
            }
        } catch (e: CursorIndexOutOfBoundsException) {
            Log.e("CursorException", "CursorIndexOutOfBoundsException: ${e.message}")
        } finally {
            cursor?.close()
        }

        return path
    }
}
