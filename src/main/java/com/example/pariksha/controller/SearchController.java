//package com.example.pariksha.controller;
//
//import com.example.pariksha.model.SearchItem;
//
//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.ws.rs.QueryParam;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//
//@Path("/api/search")
//public class SearchController {
//    @GET
//    public Response search(@QueryParam("term") String searchTerm,
//                           @QueryParam("make") String makeFilter,
//                           @QueryParam("model") String modelFilter)
//                           // Add more filter parameters as needed
//    {
//        List<SearchItem> searchResults = new ArrayList<>();
//        // Perform the search and add the matching items to the searchResults list
//        // You will need to modify this code to fit your specific database schema and search algorithm
//        // Example code:
//        List<SearchItem> items = getItemsFromDatabase();
//        for (SearchItem item : items) {
//            if (item.getExamName().toLowerCase().contains(searchTerm.toLowerCase())) {
//                if (makeFilter.isEmpty() || item.getDepartment().toLowerCase().equals(makeFilter.toLowerCase())) {
//                    if (modelFilter.isEmpty() || item.getSubName().toLowerCase().equals(modelFilter.toLowerCase())) {
//                        // Add more filter checks as needed
//                        searchResults.add(item);
//                    }
//                }
//            }
//        }
//        // Return the search results as JSON
//        return Response.ok(searchResults).type(MediaType.APPLICATION_JSON).build();
//    }
//
//    private List<SearchItem> getItemsFromDatabase() {
//        // This method should retrieve the items from your database and return them as a list
//        // You will need to modify this code to fit your specific database schema and data access code
//        // Example code:
//        List<SearchItem> items = new ArrayList<>();
//        Connection conn = null;
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//        try {
//            conn = DriverManager.getConnection("jdbc:mysql://localhost/mydatabase", "myuser", "mypassword");
//            String sql = "SELECT * FROM items";
//            stmt = conn.prepareStatement(sql);
//            rs = stmt.executeQuery();
//            while (rs.next()) {
//                Item item = new Item();
//                item.setName(rs.getString("name"));
//                item.setMake(rs.getString("make"));
//                item.setModel(rs.getString("model"));
//                item.setYear(rs.getInt("year"));
//                item.setColor(rs.getString("color"));
//                item.setMileage(rs.getInt("mileage"));
//                // Add more columns as needed
//                items.add(item);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (rs != null) rs.close();
//                if (stmt != null) stmt.close();
//                if (conn != null) conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return items;
//    }
//
//}
