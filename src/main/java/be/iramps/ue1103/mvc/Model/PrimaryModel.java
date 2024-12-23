package be.iramps.ue1103.mvc.Model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

import be.iramps.ue1103.mvc.Model.BL.Section;
import be.iramps.ue1103.mvc.Model.BL.Status;
import be.iramps.ue1103.mvc.Model.DAL.Sections.ISectionDAO;
import be.iramps.ue1103.mvc.Model.DAL.Sections.SectionDAO;
import be.iramps.ue1103.mvc.Model.DAL.Status.IStatusDAO;
import be.iramps.ue1103.mvc.Model.DAL.Status.StatusDAO;

public class PrimaryModel implements IModel {
    private PropertyChangeSupport support;
    private ISectionDAO iSectionDAO;
    private IStatusDAO iStatusDAO;

    public PrimaryModel() {
        this.support = new PropertyChangeSupport(this);
        this.iSectionDAO = new SectionDAO("jdbc:postgresql://localhost/", "postgres", "Test01");
        this.iStatusDAO = new StatusDAO(this.iSectionDAO.getConnection());
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }

    // Gestion des sections
    @Override
    public void getAllSection() {
        ArrayList<Section> sections = this.iSectionDAO.getSections();
        ArrayList<String> sectionsName = new ArrayList<>();
        for (Section section : sections) {
            sectionsName.add(section.getNom());
        }
        support.firePropertyChange("listeSection", "", sectionsName);
    }

    @Override
    public void getSection(String sectionName) {
        int id = this.iSectionDAO.getIDSection(sectionName);
        ArrayList<String> infoSection = new ArrayList<>();
        infoSection.add(Integer.toString(id));
        infoSection.add(sectionName);
        support.firePropertyChange("sectionSelected", "", infoSection);
    }

    @Override
    public void deleteSection(String id) {
        this.iSectionDAO.deleteSection(Integer.parseInt(id));
        this.getAllSection();
    }

    @Override
    public void updateSection(String id, String nom) {
        this.iSectionDAO.updateSection(Integer.parseInt(id), nom);
        this.getSection(nom);
    }

    @Override
    public void insertSection(String nom) {
        this.iSectionDAO.addSection(nom);
        this.getSection(nom);
    }

    @Override
    public void close() {
        this.iSectionDAO.close();
        this.iStatusDAO.close();
    }

    // Gestion des statuts
    @Override
    public void getAllStatus() {
        ArrayList<Status> statuses = this.iStatusDAO.getStatus();
        ArrayList<String> statusNames = new ArrayList<>();
        for (Status status : statuses) {
            statusNames.add(status.getNom());
        }
        support.firePropertyChange("listeStatus", "", statusNames);
    }

    @Override
    public void getStatus(String statusName) {
        int id = this.iStatusDAO.getIDStatus(statusName);
        ArrayList<String> infoStatus = new ArrayList<>();
        infoStatus.add(Integer.toString(id));
        infoStatus.add(statusName);
        support.firePropertyChange("statusSelected", "", infoStatus);
    }

    @Override
    public void deleteStatus(String id) {
        this.iStatusDAO.deleteStatus(Integer.parseInt(id));
        this.getAllStatus();
    }

    @Override
    public void updateStatus(String id, String nom) {
        this.iStatusDAO.updateStatus(Integer.parseInt(id), nom);
        this.getStatus(nom);
    }

    @Override
    public void insertStatus(String nom) {
        this.iStatusDAO.addStatus(nom);
        this.getStatus(nom);
    }
}
