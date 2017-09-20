package svs.timetracker.domain.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Project extends SpendingTimeCause implements Nameable {
    @PrimaryKey
    @ColumnInfo(name = "projectName")
    private String name;

    public Project(String name) {
        setName(name);
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        cause = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Project)) {
            return false;
        }
        final Project project = (Project) obj;
        return project.getName().equals(name);
    }
}
