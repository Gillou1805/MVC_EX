package be.iramps.ue1103.mvc.Model.DAL.Sections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import be.iramps.ue1103.mvc.Model.BL.Section;

public class SectionDAO implements ISectionDAO {
    Connection connexion;
    PreparedStatement insertSection;   
    PreparedStatement updateSection;
    PreparedStatement deleteSection;
    PreparedStatement getIDSection;
    PreparedStatement getSections;


    public SectionDAO(String url, String user, String password) {
        try {
            this.connexion = DriverManager.getConnection(url, user, password);
            Statement statement = connexion.createStatement();
            try {
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS Section (id SERIAL PRIMARY KEY, nom VARCHAR(30))");
            } catch (SQLException e) {
                // La table existe déjà. Log pour le cas où.
                System.out.println(e.getMessage());
            }
            statement.close();
            this.insertSection = this.connexion.prepareStatement("INSERT into Section (nom) VALUES (?)");
            this.updateSection = this.connexion.prepareStatement("UPDATE Section SET nom=? WHERE id=?");
            this.deleteSection = this.connexion.prepareStatement("DELETE FROM Section WHERE id=?");
            this.getIDSection = this.connexion.prepareStatement("SELECT id FROM Section WHERE nom=?");
            this.getSections = this.connexion.prepareStatement("SELECT id,nom FROM Section");

            ;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean close() {
        boolean ret = true;
        if (this.updateSection != null) {
            try {
                this.updateSection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                ret = false;
            }
        }

        if (this.getIDSection != null) {
            try {
                this.getIDSection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                ret = false;
            }
        }
        if (this.deleteSection != null) {
            try {
                this.deleteSection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                ret = false;
            }
        }
        
        if (this.getSections != null) {
            try {
                this.getSections.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                ret = false;
            }
        }
        
        if (this.insertSection != null) {
            try {
                this.insertSection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                ret = false;
            }
        }
        if (this.connexion != null) {
            try {
                this.connexion.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                ret = false;
            }
        }

        return ret;
    }

    @Override
    public ArrayList<Section> getSections() {
        ArrayList<Section> listeSection = new ArrayList<Section>();
        try {
            ResultSet set = this.getSections.executeQuery();
            while (set.next()) {
                Section section = new Section(set.getInt(1), set.getString(2));
                listeSection.add(section);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listeSection;

    }

    @Override
    public int getIDSection(String nom) {
        int id = -1;
        try {
            this.getIDSection.setString(1, nom);
            ResultSet set = this.getIDSection.executeQuery();
            while (set.next()) {
                id = set.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return id;
    }

    @Override
    public boolean updateSection(int id, String nom) {
        try {        
            this.updateSection.setString(1, nom);
            this.updateSection.setInt(2, id);    
            this.updateSection.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteSection(int id) {
        try {
            this.deleteSection.setInt(1, id);
            this.deleteSection.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean addSection(String nom) {
        try {
            this.insertSection.setString(1, nom);
            this.insertSection.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    public Connection getConnection() {
        if (this.connexion == null) {
            System.out.println("Erreur : la connexion est nulle !");
        }
        return this.connexion;
    }

    


}
