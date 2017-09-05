package svs.timetracker.domain.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Project extends SpendingTimeCause {
    @PrimaryKey
    private String name;

    public Project(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        cause = name;
    }
}
