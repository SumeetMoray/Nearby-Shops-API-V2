package org.nearbyshops.Templates.DAOs;

import com.zaxxer.hikari.HikariDataSource;
import org.nearbyshops.Globals.Globals;
import org.nearbyshops.Model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by sumeet on 13/12/16.
 */
public class PreparedBulkUpdates {

    private HikariDataSource dataSource = Globals.getDataSource();


    public int changeParentBulk(List<Item> itemList)
    {

        String updateStatement = "UPDATE " + Item.TABLE_NAME

                + " SET "

//				+ " " + Item.ITEM_NAME + " = ?,"
//				+ " " + Item.ITEM_DESC + " = ?,"
//				+ " " + Item.ITEM_IMAGE_URL + " = ?,"

                + " " + Item.ITEM_CATEGORY_ID + " = ?"
//				+ " " + Item.QUANTITY_UNIT + " = ?,"
//				+ " " + Item.ITEM_DESCRIPTION_LONG + " = ?"

                + " WHERE " + Item.ITEM_ID + " = ?";


        Connection connection = null;
        PreparedStatement statement = null;

        int rowCountUpdated = 0;

        try {

            connection = dataSource.getConnection();
            statement = connection.prepareStatement(updateStatement);


            for(Item item : itemList)
            {
//				statement.setString(1,item.getItemName());
//				statement.setString(2,item.getItemDescription());
//				statement.setString(3,item.getItemImageURL());
                if(item.getItemCategoryID()!=null && item.getItemCategoryID()==-1)
                {
                    item.setItemCategoryID(null);
                }

                statement.setObject(1,item.getItemCategoryID());
//				statement.setString(5,item.getQuantityUnit());
//				statement.setString(6,item.getItemDescriptionLong());
                statement.setInt(2,item.getItemID());

                statement.addBatch();
            }


            int[] totalsArray = statement.executeBatch();

            for(int i : totalsArray)
            {
                rowCountUpdated = rowCountUpdated + i;
            }

            System.out.println("Total rows updated: UPDATE BULK " + rowCountUpdated);


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally

        {

            try {

                if(statement!=null)
                {statement.close();}
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            try {

                if(connection!=null)
                {connection.close();}
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return rowCountUpdated;
    }



    public int updateItemBulk(List<Item> itemList)
    {

        String updateStatement = "UPDATE " + Item.TABLE_NAME

                + " SET "

                + " " + Item.ITEM_NAME + " = ?,"
                + " " + Item.ITEM_DESC + " = ?,"
                + " " + Item.ITEM_IMAGE_URL + " = ?,"

                + " " + Item.ITEM_CATEGORY_ID + " = ?,"
                + " " + Item.QUANTITY_UNIT + " = ?,"
                + " " + Item.ITEM_DESCRIPTION_LONG + " = ?"

                + " WHERE " + Item.ITEM_ID + " = ?";


        Connection connection = null;
        PreparedStatement statement = null;

        int rowCountUpdated = 0;

        try {

            connection = dataSource.getConnection();
            statement = connection.prepareStatement(updateStatement);


            for(Item item : itemList)
            {
                statement.setString(1,item.getItemName());
                statement.setString(2,item.getItemDescription());
                statement.setString(3,item.getItemImageURL());

                statement.setInt(4,item.getItemCategoryID());
                statement.setString(5,item.getQuantityUnit());
                statement.setString(6,item.getItemDescriptionLong());
                statement.setInt(7,item.getItemID());

                statement.addBatch();
            }


            int[] totalsArray = statement.executeBatch();

            for(int i : totalsArray)
            {
                rowCountUpdated = rowCountUpdated + i;
            }

            System.out.println("Total rows updated: UPDATE BULK " + rowCountUpdated);


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally

        {

            try {

                if(statement!=null)
                {statement.close();}
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            try {

                if(connection!=null)
                {connection.close();}
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return rowCountUpdated;
    }


}
