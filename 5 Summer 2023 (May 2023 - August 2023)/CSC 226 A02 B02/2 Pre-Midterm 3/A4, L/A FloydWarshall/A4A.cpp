#include <iostream>
using namespace std;

// Number of vertices in the Graph
#define V 5
#define INF 99999

// A function to print the solution matrix
void print_Solution(int distance[][V]);
void floyd_Warshall(int Graph[][V])
{
    /* distance[][] will be the output matrix */
    int distance[V][V], i, j, k;

    for (i = 0; i < V; i++)
        for (j = 0; j < V; j++)
            distance[i][j] = Graph[i][j];

    for (k = 0; k < V; k++)
    {
        // Pick all vertices as source one by one
        for (i = 0; i < V; i++)
        {
            for (j = 0; j < V; j++)
            {

                if (distance[i][k] + distance[k][j] < distance[i][j])
                    distance[i][j] = distance[i][k] + distance[k][j];
            }
        }
        print_Solution(distance);
    }

    // Print the shortest distanceance matrix
    // print_Solution(distance);
}

/* A utility function to print solution */
void print_Solution(int distance[][V])
{
    cout << "The following matrix shows the shortest distanceances"
            " between every pair of vertices \n";
    for (int i = 0; i < V; i++)
    {
        for (int j = 0; j < V; j++)
        {
            if (distance[i][j] == INF)
                cout << "INF"
                     << " ";
            else
                cout << distance[i][j] << " ";
        }
        cout << endl;
    }
}

// Driver code
int main()
{
    /*
    Let's take the nodes starting from 0 ,since the given input diagram has starting node 1 , we will assume it as 0 and similarly for other nodes as well.Node 1 as Node 0..Node 2 as Node 1..etc
    */

    int Graph[V][V] = {{0, 3, 8, INF, -4},
                       {INF, 0, INF, 1, 7},
                       {INF, 4, 0, INF, INF},
                       {2, INF, -5, 0, INF},
                       {INF, INF, INF, 6, 0}

    };

    // Print the solution
    floyd_Warshall(Graph);
    return 0;
}