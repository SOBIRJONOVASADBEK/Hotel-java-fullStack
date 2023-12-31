package Book.modul;

public class Genre {
    
    private String name;
    private int id;
    private static int count = 1;
    {
        id = count++;
    }
    
    public Genre(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "modulS.Genre{" +
                "name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
