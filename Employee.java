import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.*;
import java.time.format.DateTimeFormatter; 

class People
{
  public String Name;
  public String Age;
  public String Gender;
  
  public People(String Name,String age,String Gender)
  {
    this.Name=Name;
    this.Age=age;
    this.Gender=Gender;
  }
}


class ElectionCommission extends People{
  public String username;
  public String password;

  public ElectionCommission(String Name,String age,String Gender,String username,String password)
  {
    super(Name,age,Gender);
    this.username=username;
    this.password=password;
  }
}

class Voters extends People{
  public String LoginID;
  public String password;
  public String V_or_NV;

  public Voters(String Name,String age,String Gender,String LoginID,String password,String V_or_NV)
  {
    super(Name,age,Gender);
    this.LoginID=LoginID;
    this.password=password;
    this.V_or_NV=V_or_NV;
  }

  public void getInput() throws MinorCitizenException{
    System.out.println("\t\t\t\tVoters Enrollment Form");
    Scanner sc=new Scanner(System.in);
    System.out.println("Enter Name: ");
    this.Name=sc.next();
    System.out.println("Enter Age: ");
    this.Age=sc.next();
    try{
      int i=Integer.parseInt(Age);
      try{
        if(i<25){
            throw new MinorCitizenException("Age is not valid to vote");
          }
        }catch (MinorCitizenException e){
          System.out.println(e);

          try {
                Thread.sleep(3000L);
                getInput();
                } catch(InterruptedException ie) {
                    ie.printStackTrace();
              }
          }

      }catch (NumberFormatException e){
        System.out.println(e+": Age includes extra characters other than numbers !!!");
        try {
                Thread.sleep(3000L);
                getInput();
                } catch(InterruptedException ie) {
                    ie.printStackTrace();
              }
      }
    System.out.println("Enter Gender: ");
    this.Gender=sc.next();
    System.out.println("Enter a LoginId: ");
    this.LoginID=sc.next();
    System.out.println("Enter a Password : ");
    this.password=sc.next();
    this.V_or_NV="0";
  }

  public String getName(){
    return this.Name;
  }

  public String getAge(){
    return this.Age;
  }

  public String getGender(){
    return this.Gender;
  }

  public String getUserId(){
    return this.LoginID;
  }

  public String getPassword(){
    return this.password;
  }

  public void display(){
    System.out.println("Name : "+Name);
    System.out.println("Age : "+Age);
    System.out.println("Gender : "+Gender);
  }

}

class Candidates extends People
{
  public String Party;
  public String Symbol;

  public Candidates(String Name,String age,String Gender,String Party,String Symbol)
  {
    super(Name,age,Gender);
    this.Party=Party;
    this.Symbol=Symbol;
  }
}
public class Employee
{
    public static void result()
    {
        String str="";
        try
        {
            str = new String (Files.readAllBytes( Paths.get("data/Candidates.txt") ) );
            String records[]=str.split("\n");
            String[][] fields = new String[records.length][6];
            for(int i=0;i<records.length;i++) 
            {
                String[] temp=records[i].split(":");
                for(int j=0;j<temp.length;j++) 
                {
                    fields[i][j] = temp[j];
                }
            }
            int max=Integer.parseInt(fields[0][5]);
            int maxpos=0;
            for(int i=1;i<records.length;i++){
              int x=Integer.parseInt(fields[i][5]);
              if(x>max){
                max=x;
                maxpos=i;
              }
            }
            System.out.println("The Winner is : "+records[maxpos]);
        }catch (IOException e) 
        {
            e.printStackTrace();
        }
        
    }
    public static void UpdateCount(int candidate)
    {
        String str="";
        try
        {
            str = new String (Files.readAllBytes( Paths.get("data/Candidates.txt") ) );
            String records[]=str.split("\n");
            String[][] fields = new String[records.length][6];
            for(int i=0;i<records.length;i++) 
            {
                String[] temp=records[i].split(":");
                for(int j=0;j<temp.length;j++) 
                {
                    fields[i][j] = temp[j];
                }
            }
            if(candidate>=0 && candidate<=4)
            {
                int x=Integer.parseInt(fields[candidate][5]);
                fields[candidate][5]=String.valueOf(x+1);
            }
            String writ="";
            for(int i=0;i<records.length;i++)
            {
                String temp="";
                for(int j=0;j<6;j++)
                {
                    if(j<5)
                    {
                        temp+=fields[i][j]+":";
                    }
                    else temp+=fields[i][j]+"\n";
                }
                writ+=temp;   
            }
            try {
                FileWriter f = new FileWriter("data/Candidates.txt");
                f.write(writ);
                f.close();
                
                } 
            catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
                }
        }catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
    public static void UpdateVoter(int voternumber)
    {
        String str="";
        try
        {
            str = new String (Files.readAllBytes( Paths.get("data/Voters1.txt") ) );
            String records[]=str.split("\n");
            String[][] fields = new String[records.length][6];
            for(int i=0;i<records.length;i++) 
            {
                String[] temp=records[i].split(":");
                for(int j=0;j<temp.length;j++) 
                {
                    fields[i][j] = temp[j];
                }
            }
            fields[voternumber][5]="1";
            String writ="";
            for(int i=0;i<records.length;i++)
            {
                String temp="";
                for(int j=0;j<6;j++)
                {
                    if(j<5)
                    {
                        temp+=fields[i][j]+":";
                    }
                    else temp+=fields[i][j]+"\n";
                }
                writ+=temp;
                
            }
                try {
                FileWriter f = new FileWriter("data/Voters1.txt");
                f.write(writ);
                f.close();
                
                } 
            catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
                }
        }catch (IOException e) 
        {
            e.printStackTrace();
        }
        
    }
    public static void CMenu()
    {
        String str="";
        try
        {
            str = new String (Files.readAllBytes( Paths.get("data/Candidates.txt") ) );
            String records[]=str.split("\n");
            String[][] fields = new String[records.length][6];
            for(int i=0;i<records.length;i++) 
            {
                String[] temp=records[i].split(":");
                for(int j=0;j<temp.length;j++) 
                {
                    fields[i][j] = temp[j];
                }
            }
            System.out.println("Candidate List");
            for(int i=0;i<records.length;i++) 
            {
                System.out.printf("%d . Name : "+fields[i][0]+"\tParty : "+fields[i][3]+"\tSymbol : "+fields[i][4],i+1).println();
            }
        }catch (IOException e) 
        {
            e.printStackTrace();
        }        
    }
    public static void addVoter() throws MinorCitizenException{
      String str="";
        try
        {
        System.out.print("\033[H\033[2J");

          System.out.println("\t\t\t\tVoters Enrollment Form");
          Scanner sc=new Scanner(System.in);
          System.out.println("Enter Name: ");
          String Name=sc.next();
          System.out.println("Enter Age: ");
          String Age=sc.next();
          try{
          int i=Integer.parseInt(Age);
          try{
            if(i<18){
                throw new MinorCitizenException("Age is not valid to vote");
            }
          }catch (MinorCitizenException e){
            System.out.println(e);
            try {
                
                    Thread.sleep(3000L);
                    addVoter();
                    } catch(InterruptedException ie) {
                        ie.printStackTrace();
                        

                }
            }

          }catch (NumberFormatException e){
            System.out.println(e+": Age includes extra characters other than numbers !!!");
            try {
                    addVoter();

                    Thread.sleep(3000L);
                    } catch(InterruptedException ie) {
                        ie.printStackTrace();

                }
          }
          System.out.println("Enter Gender: ");
          String Gender=sc.next();
          System.out.println("Enter a LoginId: ");
          String LoginID=sc.next();
          System.out.println("Enter a Password : ");
          String password=sc.next();
          String votes="0";
          str += new String(Files.readAllBytes( Paths.get("data/Voters1.txt")));
          if(str.contains(password) || str.contains(LoginID)){
            System.out.println("\t\t\tEnter another Login id and Password\n");
            try {
            Thread.sleep(3000L);
            if(LocalTime.now().isBefore( LocalTime.parse( "23:00" )))
            {
              addVoter();
            }
            }catch(InterruptedException ie) {
                    ie.printStackTrace();
                    
                }
          } 
          else{
            str+=Name+":"+Age+":"+Gender+":"+LoginID+":"+password+":"+votes+"\n";
            try {
              FileWriter f = new FileWriter("data/Voters1.txt");
              f.write(str);
              f.close();
              } 
            catch (IOException e) {
              System.out.println("An error occurred.");
              e.printStackTrace();
              }
          }
      }
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        System.out.println("\t\tVoter added!!!");
        try {
            Thread.sleep(3000L);
            if(LocalTime.now().isBefore( LocalTime.parse( "23:00" )))
            {
              addVoter();
            }
            }catch(InterruptedException ie) {
                    ie.printStackTrace();
                    
                }
          
    }
  
    public static void VoterMenu() throws MinorCitizenException
    {
      Scanner sc=new Scanner(System.in);
      System.out.print("\033[H\033[2J");
      DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/YYYY");  
      LocalDateTime now = LocalDateTime.now();  
      System.out.println("\t\t\t\tElection Day : "+date.format(now)+"\n\t\t\t   Your Future is in your hands👐");

      System.out.print("\nEnter UserId : ");
      String uid=sc.next();
      System.out.print("\nEnter password : ");
      String pswrd=sc.next();
      String str="";
      try
      {
        str = new String (Files.readAllBytes( Paths.get("data/Voters1.txt") ) );
        String records[]=str.split("\n");
        String[][] fields = new String[records.length][6];
        for(int i=0;i<records.length;i++) 
        {
            String[] temp=records[i].split(":");
            for(int j=0;j<temp.length;j++) 
            {
                fields[i][j] = temp[j];
            }
        }
        
        int flag=0;
        for(int i=0;i<records.length;i++){
          if(uid.equals(fields[i][3]) && pswrd.equals(fields[i][4])){
            flag=1;
            Voters v1=new Voters(fields[i][0],fields[i][1],fields[i][2],fields[i][3],fields[i][4],fields[i][5]);            
            if(fields[i][5].equals("0")){
              v1.display();
              System.out.print("\nVerify your details\nEnter 1 to procees further\nEnter 0 to exit\n");
              int choice=sc.nextInt();
              if(choice==1){
                CMenu();
                int select;
                  System.out.print("\nEnter your choice:");
                  select=sc.nextInt();
                  UpdateCount(select-1);
                  UpdateVoter(i);
                  System.out.println("\t\t\t\tYour Vote has been recorded!!!");
                try {
                  Thread.sleep(3000L);
                  if(LocalTime.now().isBefore( LocalTime.parse( "22:00" )))
                  {
                      VoterMenu();
                  }
                  else
                  {
                      try {
      
                        System.out.print("\033[H\033[2J");
                        System.out.print("\nTIMEUP");

                        Thread.sleep(10000L);
                    }catch(InterruptedException ie) {
                    ie.printStackTrace();
                    }
                  }
                } catch(InterruptedException ie) {
                    ie.printStackTrace();
                }
              }
            }
            else
            {
              System.out.println("\t\t\t\tYou have already Voted!!!");
              try {
                Thread.sleep(3000L);
                if(LocalTime.now().isBefore( LocalTime.parse( "20:00" )))
                {
                      VoterMenu();
                }
                else
                {
                    try {
                        System.out.print("\033[H\033[2J");
                        System.out.print("\n!!!TIMEUP!!!");
                        Thread.sleep(10000L);
                    }catch(InterruptedException ie) {
                    ie.printStackTrace();
                    }
                  }
                } catch(InterruptedException ie) {
                    ie.printStackTrace();
              }
            }
          }
        }
        if(flag==0){
          System.out.println("\t\t\t\tWrong UserId or Password\n\t\t\t\tRe-enter your details");
          try {
                Thread.sleep(3000L);
                VoterMenu();
                } catch(InterruptedException ie) {
                    ie.printStackTrace();
              }
          }
      }catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
    public static void callVoting() throws AgeLessForContestInElextionsException,MinorCitizenException
    {
      Scanner sc=new Scanner(System.in);
      System.out.print("\033[H\033[2J");
      System.out.println("Start Voting????:\nEnter YES or NO");
      String ans=sc.next();
      if(ans.equals("YES")){
        VoterMenu();
      }
      ECMenu();
    }
    public static void addCand() throws AgeLessForContestInElextionsException,MinorCitizenException{
      Scanner sc=new Scanner(System.in);
      String Name,str="";
      String Age;
      String Gender;
      System.out.println("Enter Name:");
      Name=sc.next();
      System.out.println("Enter Age:");
      Age=sc.next();
      try{
      int i=Integer.parseInt(Age);
      try
      {
        if(i<25)
        {
            throw new AgeLessForContestInElextionsException("Age is not valid to contest in an Election\n\t\tRe-enter the details");
          }
        }catch (AgeLessForContestInElextionsException e){
          System.out.println(e);
          try {
                Thread.sleep(3000L);
                ECMenu();
                } catch(InterruptedException ie) {
                    ie.printStackTrace();
              }
          }

      }catch (NumberFormatException e){
        System.out.println(e+" : Age includes extra characters other than numbers!!!\n\t\tRe-enter the details");
        try{
                Thread.sleep(3000L);
                ECMenu();
                }catch(InterruptedException ie) {
                    ie.printStackTrace();
                }
        }
      System.out.println("Enter Gender:");        
      Gender=sc.next();
      System.out.println("Enter Party:");
      String Party=sc.next();
      System.out.println("Enter Symbol:");
      String Symbol=sc.next();
      int Votes=0;
      try
      {
          str += new String (Files.readAllBytes( Paths.get("data/Voters1.txt") ) );
          str+=Name+":"+Age+":"+Gender+":"+Party+":"+Symbol+":"+Votes+"\n";
          try {
            FileWriter f = new FileWriter("data/Voters1.txt");
            f.write(str);
            f.close();
            } 
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            }
      }catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
    public static void ECMenu() throws AgeLessForContestInElextionsException,MinorCitizenException
    {
        int flag=0;
        System.out.print("\033[H\033[2J");
        Scanner sc=new Scanner(System.in);
        String user,pswd;
        DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/YYYY");  
        LocalDateTime now = LocalDateTime.now();  
        System.out.println("\t\t\t\tElection Day : "+date.format(now));
        System.out.println("\n\t\t\t\tElection Commission Board\n\t\t\t\t\t\t  ADMIN");
        System.out.print("\nEnter Username:");
        user=sc.nextLine();
        System.out.print("\nEnter Password:");
        pswd=sc.nextLine();  
        String str="";
        try
        {
            str += new String (Files.readAllBytes( Paths.get("data/admin.txt") ) );
            String records[]=str.split("\n");
            String[][] fields = new String[records.length][6];
            for(int i=0;i<records.length;i++) 
            {
                String[] temp=records[i].split(":");
                for(int j=0;j<temp.length;j++) 
                {
                    fields[i][j] = temp[j];
                }
            }
            for(int i=0;i<fields.length;i++) 
            {
                if(user.equals(fields[i][3]) && pswd.equals(fields[i][4])){
                  flag=1;
                  break;
                }
            }
            if(flag==0){
              System.out.println("Check your Userid and Password and Re-enter it again");
              try {
                    Thread.sleep(3000L);
                    } catch(InterruptedException ie) {
                        ie.printStackTrace();

                }
                ECMenu();
            }
            else if(flag==1){
              System.out.println("MENU\n1 to call Voting :\n2 to display Result:\n3 to add Candidate\n4.Open for the Enrollment of Voters:\n\n");
              int choice=sc.nextInt();
              if(choice==1){
                callVoting();                
              }
              else if(choice==2){
                result();
              }
              else if(choice==3){
                addCand();
              }
              else if(choice==4){
                addVoter();
              }
            }
            else ECMenu();
            }catch (IOException e){
            e.printStackTrace();
            }
    }
    public static void main(String[] args) throws AgeLessForContestInElextionsException,MinorCitizenException{
        ECMenu();
    }
}


class AgeLessForContestInElextionsException extends Exception{
  public AgeLessForContestInElextionsException(String str){
    super(str);
  }
}

class MinorCitizenException extends Exception{
  public MinorCitizenException(String str){
    super(str);
  }
}
