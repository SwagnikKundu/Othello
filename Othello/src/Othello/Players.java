package Othello;

public class Players{

    private  String name;
    private  char color;

    public Players(String name, char color){
        setName(name);
        setColor(color);
    }

    public void setName(String name){
        if(!name.isEmpty())
            this.name=name;

    }

    public String getName(){
        return this.name;
    }

    public void setColor(char color){
        if(color!='\0')
            this.color=color;
    }

    public char getColor(){
        return this.color;
    }

}