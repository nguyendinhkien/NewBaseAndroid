package vn.nguyendinhkien.appcommon.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import vn.nguyendinhkien.appcommon.data.source.local.entities.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: UserEntity)

    @Query("SELECT * FROM USER ORDER BY id ASC")
    fun getAllUser(): Flow<List<UserEntity>>

    @Query("SELECT * FROM USER WHERE id = :id")
    fun getUserById(id: Int): Flow<UserEntity?>

    @Query("DELETE FROM USER WHERE id = :id")
    suspend fun deleteUser(id: Int)
}