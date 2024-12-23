package be.iramps.ue1103.mvc.Model.DAL.Sections;

import java.sql.Connection;
import java.util.ArrayList;

import be.iramps.ue1103.mvc.Model.BL.Section;

public interface ISectionDAO {

    public ArrayList<Section> getSections();

    public int getIDSection(String nom);

    public boolean updateSection(int id, String nom);

    public boolean deleteSection(int id);

    public boolean addSection(String nom);

    public boolean close();
    
    public Connection getConnection() ;

    
    
}
