using System;

namespace FillWithColor
{
    class Program
    {
        static int[,] matrix = new int[,] {
            {   0,   0,   1, 100, 100, 100 },
            {   0, 100,   1, 100, 100, 100 },
            {   1,   1,   1, 100,   3,   3 },
            {   1, 100, 100, 100,   3,   3 },
            {   1, 100, 100, 100,   3,   3 },
        };

        static void Main(string[] args)
        {
            Print();

            Console.WriteLine();
            ChangeColor(2, 3, 999);
            Print();

            Console.WriteLine();
            ChangeColor(0, 0, 777);
            Print();

            Console.WriteLine();
            ChangeColor(0, 2, 777);
            Print();

            Console.WriteLine();
            ChangeColor(0, 0, 555);
            Print();
        }

        static void ChangeColor(int i, int j, int newColor)
        {
            if (i < 0) return;
            if (i >= matrix.GetLength(0)) return;

            if (j < 0) return;
            if (j >= matrix.GetLength(1)) return;

            int oldColor = matrix[i, j];
            if (oldColor == newColor) return;

            ChangeColor(i, j, newColor, oldColor);
        }

        static void ChangeColor(int i, int j, int newColor, int oldColor)
        {
            if (i < 0) return;
            if (i >= matrix.GetLength(0)) return;

            if (j < 0) return;
            if (j >= matrix.GetLength(1)) return;

            int currentColor = matrix[i, j];
            if (currentColor != oldColor) return;
            if (currentColor == newColor) return;

            matrix[i,j] = newColor;

            ChangeColor(i+1, j, newColor, oldColor);
            ChangeColor(i-1, j, newColor, oldColor);
            ChangeColor(i, j+1, newColor, oldColor);
            ChangeColor(i, j-1, newColor, oldColor);
        }

        static void Print()
        {
            int maxI = matrix.GetLength(0);
            int maxJ = matrix.GetLength(1);

            for (int i = 0; i < maxI; i++)
            {
                for (int j = 0; j < maxJ; j++)
                {
                    Console.Write("+-----");
                }

                Console.WriteLine("+");

                for (int j = 0; j < maxJ; j++)
                {
                    Console.Write("| {0,3} ", matrix[i, j]);
                }

                Console.WriteLine("|");
            }

            for (int j = 0; j < maxJ; j++)
            {
                Console.Write("+-----");
            }

            Console.WriteLine("+");
        }
    }
}
