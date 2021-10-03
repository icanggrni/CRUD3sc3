/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Annisa Anggraini
 */
@ManagedBean
@RequestScoped
public class dataINV {
    private String id, name, description, stock, created_at;

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
    
    public ArrayList<dataINV> getGet_all_dataINV(){
        ArrayList<dataINV> list_of_dataINV=new ArrayList<dataINV>();
        try{
            Connection connection=null;
            DB_connection obj_DB_connection=new DB_connection();
            connection=obj_DB_connection.get_connection();
            Statement st=connection.createStatement();
            
            ResultSet rs=st.executeQuery("select * from inventories");
            while(rs.next()){
                dataINV obj_dataINV=new dataINV();
                obj_dataINV.setId(rs.getString("id"));
                obj_dataINV.setName(rs.getString("name"));
                obj_dataINV.setDescription(rs.getString("description"));
                obj_dataINV.setStock(rs.getString("stock"));
                obj_dataINV.setCreated_at(rs.getString("Created_at"));
                
                list_of_dataINV.add(obj_dataINV);
        } 
        }catch (Exception e){
            System.out.println(e);
        }
        return list_of_dataINV;
    }
    
    public void add_dataINV(){
        try{
            Connection connection=null;
            DB_connection obj_DB_connection=new DB_connection();
            connection=obj_DB_connection.get_connection();
            
            PreparedStatement ps=connection.prepareStatement("insert into inventories value('"+name+"','"+description+"','"+stock+"')");
            ps.executeUpdate();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    
    public String edit_dataINV(){
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
        String field_id = params.get("action");
        try{
            DB_connection obj_DB_connection=new DB_connection();
            Connection connection=obj_DB_connection.get_connection();
            Statement st=connection.createStatement();
            
            ResultSet rs=st.executeQuery("select * from inventories where id="+field_id);
            obj_dataINV.setId(rs.getString(rs.getString("id")));
            obj_dataINV.setName(rs.getString("name"));
            
            sessionMap.put("editdataINV",obj_dataINV);
                   
        }catch (Exception e){
            System.out.println(e);
        }
        return "/edit.xhtml?faces-editrect=true";
    }
    
    public dataINV() {
    }
    
}
