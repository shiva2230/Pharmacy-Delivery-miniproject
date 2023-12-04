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

//const val BASE_URL="https://jsonplaceholder.typicode.com/"
class LoginActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var placeEditText: EditText
    private lateinit var submitButton: Button
    private lateinit var apiService: ApiService
    private lateinit var viewDataButton:Button
    private lateinit var listData:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
//        getMyData();

        nameEditText = findViewById(R.id.nameEditText)
        placeEditText = findViewById(R.id.placeEditText)
        submitButton = findViewById(R.id.button)
//        viewDataButton=findViewById(R.id.viewDataButton)
//        listData=findViewById(R.id.listData)
        apiService = RetrofitClient.getRetrofitInstance().create(ApiService::class.java)

        submitButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val pass = placeEditText.text.toString() // Use placeEditText to get the place text
            val pharmacy = Pharmacy(name, pass)

            //post
            val call: Call<Pharmacy> = apiService.createPharmacy(pharmacy)

            call.enqueue(object : Callback<Pharmacy> {
                override fun onResponse(call: Call<Pharmacy>, response: Response<Pharmacy>) {
                    if (response.isSuccessful) {
                        val createdPharmacy = response.body()
                        if (createdPharmacy != null) {
                            Toast.makeText(this@LoginActivity, "Success", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@LoginActivity, PrescriptionActivity::class.java)
                            startActivity(intent)
                            Log.d("Success", "Pharmacy created successfully")
                            Log.d("success", createdPharmacy.toString())
                        } else {
                            Log.d("Success", "Response body is null")
                        }
                    }
                }

                override fun onFailure(call: Call<Pharmacy>, t: Throwable) {
                    Log.e("Failure", "Failed to create pharmacy", t)
                }
            })



            Toast.makeText(this, "Button clicked", Toast.LENGTH_SHORT).show()
        }

//        viewDataButton.setOnClickListener {
//            //get
//            val get: Call<List<Pharmacy>> = apiService.getAllPharmacy()
//            get.enqueue(object : Callback<List<Pharmacy>> {
//                override fun onResponse(
//                    call: Call<List<Pharmacy>>,
//                    response: Response<List<Pharmacy>>
//                ) {
//                    if (response.isSuccessful) {
//                        val createdPharmacy = response.body()
//                        if (createdPharmacy != null) {
//                            val myStringBuilder = StringBuilder()
//                            for(data in createdPharmacy){
//                                myStringBuilder.append(data.name)
//                                myStringBuilder.append(data.place)
//                                myStringBuilder.append("\n")
//                            }
//                            listData.setText(myStringBuilder)
//                            Toast.makeText(this@LoginActivity, "Success", Toast.LENGTH_SHORT).show()
//                            Log.d("Success", "Pharmacy created successfully")
//                            Log.d("success", createdPharmacy.toString())
//                        } else {
//                            Log.d("Success", "Response body is null")
//                        }
//                    }
//                }
//
//                override fun onFailure(call: Call<List<Pharmacy>>, t: Throwable) {
//                    Log.e("Failure", "Failed to create pharmacy", t)
//                }
//            })
//        }

    }
}


//    private fun getMyData() {
//        val retrofitBuilder = Retrofit.Builder()
//            .addConverterFactory(GsonConverterFactory.create())
//            .baseUrl(BASE_URL)
//            .build()
//            .create(ApiService::class.java)
//
//        val retrofitData = retrofitBuilder.getData()
//        retrofitData.enqueue(object : Callback<List<MyDataItem>?>
//        {
//            override fun onResponse(
//                call: Call<List<MyDataItem>?>,
//                response: Response<List<MyDataItem>?>
//            ) {
//                val responseBody=response.body()!!
//                val myStringBuilder = StringBuilder()
//                for(myData in responseBody){
////                    myStringBuilder.append(myData.id)
//                    myStringBuilder.append(myData.title)
//
//                    myStringBuilder.append("\n")
//                }
////                val textView=findViewById<TextView>(R.id.textId)
////                textView.text=myStringBuilder
//
//            }
//
//            override fun onFailure(call: Call<List<MyDataItem>?>, t: Throwable) {
//                Log.d("MainActivity","onFailure"+t.message)
//            }
//        }
//
//
//
//        )
//
//    }

