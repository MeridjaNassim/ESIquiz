package sample;


import java.io.*;
import java.util.List;
import java.util.Map;

public class Tests {

    public static void main(String[] args) {
//        ObjectMapper mapper = new ObjectMapper();
//        String path = "data/data.json";
//        File input = new File(path);
//
//        String data = "{  \"name\" : \"nassim\", \"mark\" : 5  }";
//        String unknownData = "{ \"name\" : \"nassim\", \"mark\" : 5 , \"skills\": [\"programming\",\"sports\",\"gaming\"]}";
//        //Reader reader = new StringReader(data);
//        try {
//            Map<String , Object> attributes = mapper.readValue(unknownData,new TypeReference<Map<String,Object>>(){});
//            for (String atr: attributes.keySet()
//                 ) {
//                System.out.println("Key : "+atr+" Value : " +attributes.get(atr) +" Type of data : " + attributes.get(atr).getClass().getSimpleName());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
//
//        try {
//            List<Student> students = mapper.readValue(input,new TypeReference<List<Student>>(){});
//            for (Student student: students
//                 ) {
//                System.out.println(student.toString());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //<editor-fold desc="Writing to json with no data before">
//        Student nassim = new Student("Nassim",4);
//        Student yannis = new Student("Annis",9);
//        ObjectMapper mapper = new ObjectMapper();
//        File file = null;
//        file = new File("data/students.json");
//
//        try {
//            FileWriter writer = new FileWriter(file,true);
//            SequenceWriter sqw = mapper.writer().writeValuesAsArray(writer);
//            sqw.write(nassim);
//            sqw.write(yannis);
//            sqw.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //</editor-fold>

        

    }

}
class Student {
    private String name ;
    private int mark ;

    public Student() {
    }

    public Student(String name, int mark) {
        this.name = name;
        this.mark = mark;
    }

    public int getMark() {
        return mark;
    }

    public String getName() {
        return name;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Name : "+name+"\n"+
                "Mark : "+mark ;
    }
}
