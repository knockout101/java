// Introduction
public class introduction
{
    public static void main(String[] args)
    {
        int highScorePos = 43;
        String name = "Tristan";

        newMethod(name, highScorePos);
    }

    public static void newMethod(String name, int position)
    {
        System.out.println(name + " is in " + position);
    }
}