package com.example.sakuraboutique.CartDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.sakuraboutique.Models.ProductCartModel;

import java.util.ArrayList;
import java.util.List;

public class CartDB extends SQLiteOpenHelper {
    private static final String DB_NAME="CartDB";
    private static final int DB_VERSION=1;

    private final String CART_TABLE="CartTB";


    public CartDB(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+CART_TABLE+"(Product_ID INTEGER NOT NULL PRIMARY KEY UNIQUE,Product_Name TEXT,Quantity INTEGER,Price INTEGER,Size TEXT,Color TEXT,Url TEXT)");

    }
public boolean InsertCartItem(int ProductID,String ProductName,int Quantity,int Price,String Size,String Color,String Url)
{
SQLiteDatabase db=this.getWritableDatabase();
    ContentValues cv=new ContentValues();
    cv.put("Product_ID",ProductID);
    cv.put("Product_Name",ProductName);
    cv.put("Quantity",Quantity);
    cv.put("Price",Price);
    cv.put("Size",Size);
    cv.put("Color",Color);
    cv.put("Url",Url);

    try {
        db.insert(CART_TABLE,null,cv);
        db.close();
        return true;
    }
    catch(Exception e) {

        db.close();
        return false;
    }

}

public List<ProductCartModel> getCartItemList()
    {
        List<ProductCartModel> productCartModelList=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cs=db.rawQuery("SELECT * FROM "+CART_TABLE,null);
        if(cs.moveToFirst())
        {
            while(!cs.isAfterLast())
            {
                productCartModelList.add(new ProductCartModel(cs.getInt(cs.getColumnIndex("Product_ID")),
                        cs.getInt(cs.getColumnIndex("Price")),
                        cs.getInt(cs.getColumnIndex("Quantity")),
                        cs.getString(cs.getColumnIndex("Product_Name")),
                        cs.getString(cs.getColumnIndex("Url")),
                        cs.getString(cs.getColumnIndex("Size")),
                        cs.getString(cs.getColumnIndex("Color"))));

                cs.moveToNext();
            }

        }
        cs.close();
        db.close();
        return productCartModelList;
    }
    public void EmptyCart()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("DELETE FROM "+CART_TABLE+";");
        db.close();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
