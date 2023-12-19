package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Random

//const val BASE_URL="https://jsonplaceholder.typicode.com/"
class LoginActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var placeEditText: EditText
    private lateinit var submitButton: Button
    private lateinit var apiService: ApiService
    private lateinit var viewDataButton:Button
    private lateinit var listData:EditText
    private lateinit var emailEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)


        nameEditText = findViewById(R.id.nameEditText)
        placeEditText = findViewById(R.id.placeEditText)
        submitButton = findViewById(R.id.button)
        emailEditText = findViewById(R.id.emailEditText)

        apiService = RetrofitClient.getRetrofitInstance().create(ApiService::class.java)

        submitButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val pass = placeEditText.text.toString()
            val email = emailEditText.text.toString()

            val logNo = generateRandomLogNo()
            val pharmacy = Pharmacy(name, logNo, pass, email)


            val callPharmacy: Call<Pharmacy> = apiService.createPharmacy(pharmacy)
            callPharmacy.enqueue(object : Callback<Pharmacy> {
                override fun onResponse(call: Call<Pharmacy>, response: Response<Pharmacy>) {
                    if (response.isSuccessful) {

                        val createdPharmacy = response.body()
                        if (createdPharmacy != null) {
                            val prescImage = PrescImage(logNo, name, email)
                            val callPrescImage: Call<PrescImage> = apiService.createPrescImage(prescImage)
                            callPrescImage.enqueue(object : Callback<PrescImage> {
                                override fun onResponse(call: Call<PrescImage>, response: Response<PrescImage>) {
                                    if (response.isSuccessful) {
                                        val intent = Intent(this@LoginActivity, PrescriptionActivity::class.java)
                                        intent.putExtra("LogNo", logNo)
                                        startActivity(intent)
                                        Toast.makeText(this@LoginActivity, "Success", Toast.LENGTH_SHORT).show()
                                    } else {
                                        Log.e("Failure", "Failed to create presc image")
                                    }
                                }

                                override fun onFailure(call: Call<PrescImage>, t: Throwable) {
                                    Log.e("Failure", "Failed to create presc image", t)
                                }
                            })
                        }
                    } else {
                        Log.e("Failure", "Failed to create pharmacy")
                    }
                }

                override fun onFailure(call: Call<Pharmacy>, t: Throwable) {
                    Log.e("Failure", "Failed to create pharmacy", t)
                }
            })
        }


    }
    private fun generateRandomLogNo(): String {
        val random = Random()
        val randomNum = random.nextInt(10000)
        return "log_$randomNum"
    }
}

