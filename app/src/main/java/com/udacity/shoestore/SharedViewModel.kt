package com.udacity.shoestore

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.User

class SharedViewModel:ViewModel() {
    // list of shoe
    private var _shoeList = mutableListOf<Shoe>()
    val shoeList: MutableList<Shoe> = _shoeList
    // list of register user
    private val userList = mutableListOf<User>()
    /*
    property of shoe all live data
    */

    var shoeName = MutableLiveData<String>()
    var shoeCompany = MutableLiveData<String>()
    var shoeSize = MutableLiveData<String>()
    var shoeDescription = MutableLiveData<String>()
    // user name & password for user list
    var email = MutableLiveData<String>("")
    var pass = MutableLiveData<String>("")
    // function to add shoe object to the list of shoe.
    fun addShoe(){
        if (shoeName.value == "" || shoeSize.value == ""
            || shoeCompany.value == "" || shoeDescription.value == ""){
        }else {
            val shoe = Shoe(
                shoeName.value!!,
                shoeSize.value!!.toDouble(),
                shoeCompany.value!!,
                shoeDescription.value!!
            )
            _shoeList.add(shoe)
            propertyReset()
        }
    }
    private fun addUser(user: User){
    userList.add(user)
    }

    fun loginSuccess(): Boolean{
        if (email.value == "" || pass.value == ""){
            return false
        }else{
            val user = User(email.value!!, pass.value!!)
            if (!userList.contains(user)){
                addUser(user)
            }
            resetLoginProperty()
            return true
        }
    }
    fun propertyReset(){
        shoeName.value = ""
        shoeSize.value = ""
        shoeCompany.value = ""
        shoeDescription.value = ""
    }

    private fun resetLoginProperty(){
        email.value = ""
        pass.value = ""
    }
}