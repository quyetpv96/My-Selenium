package practiceAddData.common;
import com.opencsv.bean.CsvBindByName;
public class MyCsvData {
    @CsvBindByName(column = "title")
    private String title;

    @CsvBindByName(column = "description")
    private String description;

    @CsvBindByName(column = "relatedTo")
    private String relatedTo;

    @CsvBindByName(column = "project")
    private String project;

    @CsvBindByName(column = "points")
    private String points;

    @CsvBindByName(column = "milestone")
    private String milestone;

    @CsvBindByName(column = "assignTo")
    private String assignTo;

    @CsvBindByName(column = "collaborators")
    private String collaborators;

    @CsvBindByName(column = "status")
    private String status;

    @CsvBindByName(column = "priority")
    private String priority;

    @CsvBindByName(column = "labels")
    private String labels;

    @CsvBindByName(column = "startDate")
    private String startDate;

    @CsvBindByName(column = "deadLine")
    private String deadLine;

    @CsvBindByName(column = "repeat")
    private String repeat;

    @CsvBindByName(column = "frequency")
    private String frequency;

    @CsvBindByName(column = "cycle")
    private String cycle;

    @CsvBindByName(column = "uploadFile")
    private String uploadFile;

    public String getTitle(){
        return title;
    }

    public String getDescription(){
        return description;
    }

    public String getRelatedTo(){
        return relatedTo;
    }

    public String getProject(){
        return project;
    }

    public String getPoints(){
        return points;
    }

    public String getMilestone(){
        return milestone;
    }

    public String getAssignTo(){
        return assignTo;
    }

    public String getCollaborators(){
        return collaborators;
    }

    public String getStatus(){
        return status;
    }

    public String getPriority(){
        return priority;
    }

    public String getLabels(){
        return labels;
    }

    public String getStartDate(){
        return startDate;
    }

    public String getDeadLine(){
        return deadLine;
    }

    public String getRepeat(){
        return repeat;
    }

    public String getFrequency(){
        return frequency;
    }

    public String getCycle(){
        return cycle;
    }

    public String getUploadFile(){
        return uploadFile;
    }
}
