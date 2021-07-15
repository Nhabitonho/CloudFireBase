package com.example.cloudfirebasestorage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        1. gửi dạng map
//        Map<String,String> mapCourse = new HashMap<>();
//        mapCourse.put("Tên", "Lập trình Android");
//        db.collection("KhoaHoc")
//                .document("Android")
//                .set(mapCourse)
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()){
//                            Toast.makeText(MainActivity.this, "Thêm Thành Công", Toast.LENGTH_SHORT).show();
//                        }
//                        else{
//                            Toast.makeText(MainActivity.this, "Thất Bại", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//        Map<String,String> mapCourse1 = new HashMap<>();
//        mapCourse1.put("Tên", "Lập trình IOS");
//        db.collection("KhoaHoc")
//                .document("IOS")
//                .set(mapCourse1)
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()){
//                            Toast.makeText(MainActivity.this, "Thêm Thành Công", Toast.LENGTH_SHORT).show();
//                        }
//                        else{
//                            Toast.makeText(MainActivity.this, "Thất Bại", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
        // 2 Gui dang mang
//        Map<String , List<String>> mapNames = new HashMap<>();
//        mapNames.put("arrays", new ArrayList<>(Arrays.asList("Tèo" , "Tí" , "Tủn" , "Hoa")));
//
//        db.collection("mangten")
//                .document("array")
//                .set(mapNames)
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()){
//                            Toast.makeText(MainActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
//                        }else{
//                            Toast.makeText(MainActivity.this, "Thất bại", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//        3. gửi dạng object
//        Animal animal = new Animal("Dog", "5");
//        db.collection("Animal")
//                .document("atHome")
//                .set(animal)
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()){
//                            Toast.makeText(MainActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
//                        }else{
//                            Toast.makeText(MainActivity.this, "Thất bại", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });

//        4. gửi dạng random
//        Product product = new Product("product1", 1000);
//        db.collection("Product").add(product);

        /* ================================================================ */
//        1. đọc dữ liệu dạng string
//        DocumentReference docRef = db.collection("KhoaHoc").document("Android");
//        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
//            @Override
//            public void onEvent(@Nullable DocumentSnapshot value, @Nullable  FirebaseFirestoreException error) {
//                String chuoi = "";
//                if(value !=null && value.exists()){
//                    chuoi = (String) value.getData().get("Tên");
//                    Toast.makeText(MainActivity.this,chuoi, Toast.LENGTH_LONG).show();
//                }
//                else{
//                    System.out.print("Current Data = null");
//                }
//            }
//        });
//        2. ĐỌc dữ liệu array
//        DocumentReference arrRef = db.collection("mangten").document("array");
//        arrRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
//            @Override
//            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
//                if(value != null && value.exists()){
//                    Map<String,Object> mapData = value.getData();
//                    Iterator iterator = mapData.keySet().iterator();
//                    while(iterator.hasNext()){
//                        Log.d("BBB", mapData.get(iterator.next()).toString());
//                    }
//                }
//            }
//        });
//        3. đọc dữ liệu object
//        DocumentReference objectRef = db.collection("Animal").document("atHome");
//        objectRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
//            @Override
//            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
//                if (value != null && value.exists()){
//                    Animal animal = value.toObject(Animal.class);
//                    Log.d("BBB",animal.toString());
//                }
//            }
//        });
//        4. Đọc dư liệu dạng list document
        db.collection("Product").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(DocumentSnapshot snapshot: task.getResult().getDocuments()){
                        Map<String, Object> objectMap = snapshot.getData();
                        Iterator iterator = objectMap.keySet().iterator();
                        String name = "";
                        String price = "";
                        while(iterator.hasNext()){
                            String key = iterator.next().toString();
                            if(key.equals("price")){
                                price = objectMap.get(key).toString();
                            }
                            else{
                                name = objectMap.get(key).toString();
                            }
                        }
                        Log.d("BBB","Document SnapShot " + snapshot.getId());
                        Log.d("BBB", "Name " + name);
                        Log.d("BBB", "Price " + price);
                    }
                }
            }
        });
    }
}