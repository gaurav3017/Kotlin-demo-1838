package in.browser.fiddle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DashboardActivity extends AppCompatActivity {


    private DatabaseReference mUserDatabase;
    private FirebaseUser mCurrentUser;

    private TextView tElixir, uEmail, uPhone, uName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);


        tElixir = (TextView) findViewById(R.id.totalElixir);
        uEmail = (TextView) findViewById(R.id.userEmail);
        uPhone = (TextView) findViewById(R.id.userPhone);
        uName = (TextView) findViewById(R.id.userName);

        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();

        String name = mCurrentUser.getDisplayName();
        String lastName = "";
        String firstName= "";
        if(name.split("\\w+").length>1){

            lastName = name.substring(name.lastIndexOf(" ")+1);
            firstName = name.substring(0, name.lastIndexOf(' '));
        }
        else{
            firstName = name;
        }

        if(mCurrentUser != null) {
            uEmail.setText(mCurrentUser.getEmail());
            uPhone.setText(mCurrentUser.getPhoneNumber());
            uName.setText("Hi, " + firstName);
//            getSupportActionBar().setTitle("Hi, " + firstName);
        }

        String uid = mCurrentUser.getUid();
        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(uid).child("elixir");
        if(mUserDatabase != null) {
            mUserDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String totalElixir = dataSnapshot.getValue().toString();
                    tElixir.setText(totalElixir);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }
}
