import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DoctorDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="Doctor.db";
    private static final String TABLE_NAME="doctor_profile";
    private static final int DATABASE_VERSION=1;
    private static final String ID ="_id";
    private static final String Name ="Name";
    private static final String Address ="Address";
    private static final String Specialist="Specialist";
    private static final String PhoneNumber ="Number";
    private static final String CREATE_TABLE="Create TABLE "+TABLE_NAME+"("+ID+" INTEGER PRIMARY KEY ,"+Name+" VARCHAR(20),"+Specialist+" VARCHAR(20),"+Address+" VARCHAR(20),"+PhoneNumber+" VARCHAR(20));";
    private static final String DROP_TABLE="DROP TABLE IF EXISTS "+TABLE_NAME;
    private Context context;

    public DoctorDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            Toast.makeText(context,"onCreate method is called ", Toast.LENGTH_LONG).show();
            sqLiteDatabase.execSQL(CREATE_TABLE);
        }catch (Exception e){
            Toast.makeText(context,"Exception: "+e,Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        try {
            Toast.makeText(context,"onUpgrade method is called ",Toast.LENGTH_LONG).show();
            sqLiteDatabase.execSQL(DROP_TABLE);
            onCreate(sqLiteDatabase);

        }catch (Exception e){
            Toast.makeText(context,"Exception: "+e,Toast.LENGTH_LONG).show();
        }

    }
}
