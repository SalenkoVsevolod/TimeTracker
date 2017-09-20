package svs.timetracker.data.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import svs.timetracker.domain.model.Project;

@Dao
public interface ProjectDao {
    @Query("SELECT * FROM Project ORDER BY projectName")
    Flowable<List<Project>> getAll();

    @Query("SELECT * FROM Project where projectName = :name LIMIT 1")
    Single<Project> getByName(String name);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Project project);

    @Update
    void update(Project project);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Project> projects);

    @Delete
    void delete(Project project);
}
