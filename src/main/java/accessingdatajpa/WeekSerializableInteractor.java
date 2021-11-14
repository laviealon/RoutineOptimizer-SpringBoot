package accessingdatajpa;

import com.csc207.routop.TaskSerializable;
import com.csc207.routop.Week;
import com.csc207.routop.WeekSerializable;

import java.util.ArrayList;

public class WeekSerializableInteractor {
    WeekSerializableRepository repo;

    public void saveWeekSerializable(WeekSerializable weekSer){
        this.repo.save(weekSer);
    }

}
