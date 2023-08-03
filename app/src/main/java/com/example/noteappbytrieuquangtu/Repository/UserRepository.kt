package com.example.noteappbytrieuquangtu.Repository

import androidx.lifecycle.LiveData
import com.example.noteappbytrieuquangtu.db.User
import com.example.noteappbytrieuquangtu.db.UserDao

class UserRepository(private val userDao: UserDao) {

    val readAllData :LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }
    suspend fun updateUser(user: User){
        userDao.updateUser(user)
    }
    suspend fun deleteUser(user: User){
        userDao.deleteUser(user)
    }
    suspend fun deleteAll(){
        userDao.deleteAll()
    }
}