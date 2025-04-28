package pro1.apiDataModel;

import com.google.gson.annotations.SerializedName;

public class Specialization
{
    @SerializedName("programNazev")
    public String programName;
    
    @SerializedName("oborNazev")
    public String specializationName;
    
    @SerializedName("eprDeadlinePrihlaska")
    public Deadline deadlineApplication;
} 