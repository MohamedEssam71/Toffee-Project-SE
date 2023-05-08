package model;
import actors.Attachtments.Address;
import actors.User;
import control.shop_items.Item;
import control.shop_items.ItemStatus;
import gui.Message;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 * This Class is used to control all the queries in the toffee project database <br>
 * its functionalities:
 * <ol>
 *     <li>Add a new user to the System.</li>
 *     <li>Remove user from the System.</li>
 *     <li>Check if user found in the System.</li>
 *     <li>Print a certain query to the console.</li>
 *     <li>Load Item Menu from the System.</li>
 * </ol>
 */
public class DataBaseQueries {

    /**
     * It takes a sql query string and,
     * print what is come from the query from the database.
     * @param query
     * @throws SQLException
     */
    public void askQuery(String query) throws SQLException {
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        ResultSetMetaData resultSetMetaData;
        connection = DataBaseSystem.getConnection();
        try{
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            resultSetMetaData = resultSet.getMetaData();

            while(resultSet.next()){
                for(int i = 1; i <= resultSetMetaData.getColumnCount(); ++i){
                    System.out.print(resultSet.getString(i) + "  ");
                }
                System.out.println();
            }
        }
        catch (SQLException err){
            System.out.println(err);
        }
    }

    /**
     * Add a new user to the system database,<br>
     * If user is already found in the system it returns a message error.
     * @param user
     */
    public void addUser(User user){
        String insertUser = "INSERT INTO User(UserName,Email,Password,PhoneNumber,LoyaltyPoints," +
                "Governorate,District,Street,Landmark,BuildingNumber,Floor,FlatNumber)" +
                " VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            Connection connection = DataBaseSystem.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertUser);
            userPreparedStatement(preparedStatement,user);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Message messageBox = new Message();
            messageBox.createMessage("User is Already in System",'R');
        }
    }

    /**
     * it loads the database prepared statement with the user info.<br>
     * To be able to add the user to the system
     * @param preparedStatement
     * @param user
     * @throws SQLException
     */
    private static void userPreparedStatement(PreparedStatement preparedStatement, User user)
            throws SQLException {
        preparedStatement.setString(1,user.getUserName());
        preparedStatement.setString(2,user.getEmail());
        preparedStatement.setString(3,user.getPassword());
        preparedStatement.setString(4,user.getPhoneNumber());
        preparedStatement.setInt(5,user.getLoyaltyPoints());
        preparedStatement.setString(6,user.getAddress().getGovernorate());
        preparedStatement.setString(7,user.getAddress().getDistrict());
        preparedStatement.setString(8,user.getAddress().getStreet());
        preparedStatement.setString(9,user.getAddress().getLandmark());
        preparedStatement.setInt(10,user.getAddress().getBuildingNumber());
        preparedStatement.setInt(11,user.getAddress().getFloor());
        preparedStatement.setInt(12,user.getAddress().getFlatNumber());
    }

    /**
     * Check if a user if found in the system database or not.
     * @param user
     * @return boolean
     * @throws SQLException
     */
    public boolean checkIfUserFound(User user) throws SQLException {
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet = null;
        connection = DataBaseSystem.getConnection();
        try{
            preparedStatement = connection.prepareStatement("SELECT * FROM User");
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                if(Objects.equals(resultSet.getString(1), user.getUserName())){
                    if(Objects.equals(resultSet.getString(2), user.getEmail())){
                        return true;
                    }
                }
            }
        }
        catch (SQLException err){
            System.out.println(err);
        }
        return false;
    }

    /**
     * Delete Any specified user from the database if not found, it does nothing.
     * @param user
     * @Returns: void
     */
    public void removeUser(User user){
        String removeUser = "DELETE FROM User WHERE UserName = (?) AND Email = (?)";
        try {
            Connection connection = DataBaseSystem.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(removeUser);
            preparedStatement.setString(1,user.getUserName());
            preparedStatement.setString(2,user.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Message messageBox = new Message();
            messageBox.createMessage("Can't Remove ! User is not in the System",'R');
        }
    }


    /**
     * Get User Info from the Data Base and return to the System.
     * @param userName
     * @return User
     * @throws SQLException
     */
    public User getUser(String userName) throws SQLException {
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        connection = DataBaseSystem.getConnection();
        User user = new User();
        try{
            preparedStatement = connection.prepareStatement("SELECT * FROM User WHERE UserName = ?");
            preparedStatement.setString(1,userName);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                setUserInfo(resultSet, user);
            }
        }
        catch (SQLException err){
            System.out.println(err);
        }
        if(user.getEmail() == null){
            Message messageBox = new Message();
            messageBox.createMessage("User is Not in the System !",'R');
        }
        return user;
    }

    /**
     * Set User info the return from DataBase to the user object.
     * @param resultSet
     * @param user
     * @return void
     * @throws SQLException
     */
    private static void setUserInfo(ResultSet resultSet, User user) throws SQLException {
        user.setUserName(resultSet.getString(1));
        user.setEmail(resultSet.getString(2));
        user.setPassword(resultSet.getString(3));
        user.setPhoneNumber(resultSet.getString(4));
        user.setLoyaltyPoints(resultSet.getInt(5));

        Address address = new Address();
        address.setGovernorate(resultSet.getString(6));
        address.setDistrict(resultSet.getString(7));
        address.setStreet(resultSet.getString(8));
        address.setLandmark(resultSet.getString(9));
        address.setBuildingNumber(resultSet.getInt(10));
        address.setFloor(resultSet.getInt(11));
        address.setFlatNumber(resultSet.getInt(12));

        user.setAddress(address);
    }


    /**
     * This method return all items information from Data Base
     * then set this data in an array.
     * @return ArrayList<Item>
     * @throws SQLException
     */
    public ArrayList<Item> loadItems() throws SQLException {
        ArrayList<Item> items = new ArrayList<>();

        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        connection = DataBaseSystem.getConnection();

        try{
            preparedStatement = connection.prepareStatement("SELECT * FROM Item");
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                Item item = new Item();
                setItemInfo(resultSet, item);
                items.add(item);
            }
        }
        catch (SQLException err){
            System.out.println(err);
        }
        return items;
    }

    /**
     * Set all item info got from database to a new item object
     * @param resultSet
     * @param item
     * @throws SQLException
     */
    private static void setItemInfo(ResultSet resultSet,Item item) throws SQLException {
        item.setName(resultSet.getString(1));
        item.setDescription(resultSet.getString(2));
        item.setBrand(resultSet.getString(3));
        item.setUnitType(resultSet.getString(4));
        String status = resultSet.getString(5);

        if(Objects.equals(status, "ON_SALE")){
            item.setItemStatus(ItemStatus.ON_SALE);
        }
        else if(Objects.equals(status, "NOT_ON_SALE")){
            item.setItemStatus(ItemStatus.NOT_ON_SALE);
        }
        else{
            item.setItemStatus(ItemStatus.OUT_OF_STOCK);
        }
        item.setPrice(resultSet.getDouble(6));
    }

//    public static void main(String[] args) throws SQLException {
//        DataBaseQueries dataBaseQuery = new DataBaseQueries();
//        Address address = new Address("Giza","asd","sd","sda",45,5,8);
//        User user = new User("test","mesaads","sddsda","0865421");
//        user.setAddress(address);
//        ArrayList<Item> items = dataBaseQuery.loadItems();
//        for(int i = 0; i < items.size(); ++i){
//            System.out.println(items.get(i).getName());
//        }
//        User user = dataBaseQuery.getUser("tes");
//        System.out.println(user.getEmail());
//        dataBaseQuery.addUser(user);
//        dataBaseQuery.askQuery("SELECT * FROM User");
//        dataBaseQuery.removeUser(user);
//        dataBaseQuery.removeUser(user);
//        dataBaseQuery.askQuery("SELECT * FROM User");
//    }
}


