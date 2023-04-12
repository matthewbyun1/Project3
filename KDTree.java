package prj3;

public class KDTree {
    
    public class Node {
        double point[][];
        int depth;
        Node left, right;
        
        
        public Node(double point[][], int depth) {
            this.point = point;
            this.left = null;
            this.right = null;
            this.depth = depth;
        }
        
    }
    
    Node root;
    
    public KDTree() {
        root = null;
    }

    public Node insert(Node root, double point[][], int depth) {
        // Empty Tree, return the new node
        // The node we are currently looking at does not have any children.
        if (root == null) { 
            return new Node(point, depth);
        }
        
        // Initializing variables
        int axis = depth&3; // determines which axis we sort points by
        int l = point.length;
        double median = 0;
        double[][] left = new double[(l/2)+1][3]; // left array for values under median
        double[][] right = new double[l-(l/2)-1][3]; // right array for values above median
        
        // Sorting by x-axis
        if(axis == 1) {
            double[] x_coord = new double[l];
            for(int i = 0; i < x_coord.length;i++) {
                x_coord[i] = point[i][0];
            }
            
            mergeSort(x_coord, l); // Sorts array of points using the merge sort
            median = getMedian(x_coord); // Gets median of x-coords
            
            int left_arr = 0;
            int right_arr = 0;
            
            for(int i = 0; i < l; i++) {
                // If x-coord of point is less than or equal to median,
                // add it to the left array.
                if(point[i][0] <= median) {
                    left[left_arr] = point[i];
                    left_arr++;
                }
                // If x-coord of point is greater than median,
                // add it to the right array.
                else {
                    right[right_arr] = point[i];
                    right_arr++;
                }
            }
        }
        
        // Sorting by y-axis
        else if(axis == 2) {
            double[] y_coord = new double[l];
            for(int i = 0; i < y_coord.length;i++) {
                y_coord[i] = point[i][1];
            }
            
            mergeSort(y_coord, l); // Sorts array of points using the merge sort
            median = getMedian(y_coord); // Gets median of y-coords
            
            int left_arr = 0;
            int right_arr = 0;
            
            for(int i = 0; i < l; i++) {
                // If y-coord of point is less than or equal to median,
                // add it to the left array.
                if(point[i][1] <= median) {
                    left[left_arr] = point[i];
                    left_arr++;
                }
                // If y-coord of point is greater than median,
                // add it to the right array.
                else {
                    right[right_arr] = point[i];
                    right_arr++;
                }
            }
        }
        
        // Sorting by z-axis
        else if(axis == 0) {
            double[] z_coord = new double[l];
            for(int i = 0; i < z_coord.length;i++) {
                z_coord[i] = point[i][2];
            }
            
            mergeSort(z_coord, l); //Sorts array of points using the merge sort
            median = getMedian(z_coord); //Gets median of z-coords
            
            int left_arr = 0;
            int right_arr = 0;
            
            for(int i = 0; i < l; i++) {
                // If z-coord of point is less than or equal to median,
                // add it to the left array.
                if(point[i][2] <= median) {
                    left[left_arr] = point[i];
                    left_arr++;
                }
                // If z-coord of point is greater than median,
                // add it to the right array.
                else {
                    right[right_arr] = point[i];
                    right_arr++;
                }
            }
        }
        root.depth++; //add depth to the node
        root.left = insert(root.left, left, depth); // left child of root
        root.right = insert(root.right, right, depth); // right child of root
        
        return root;
    }
    
    /**
     * Function that finds the median value in an array
     * 
     * @return median
     */
    private double getMedian(double[] coords_arr) {
        int n = coords_arr.length;
        return coords_arr[n / 2]; 
    }
    
    /**
     * Recursive function that splits array into sub-arrays to sort array
     * 
     * @return
     */
    public static void mergeSort(double[] a, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        double[] l = new double[mid];
        double[] r = new double[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
        }
        mergeSort(l, mid);
        mergeSort(r, n - mid);

        merge(a, l, r, mid, n - mid);
    }
    
    /**
     * Merges the sub-arrays in a sorted manner
     * 
     * @return
     */
    public static void merge(
        double[] a, double[] l, double[] r, int left, int right) {
       
          int i = 0, j = 0, k = 0;
          while (i < left && j < right) {
              if (l[i] <= r[j]) {
                  a[k++] = l[i++];
              }
              else {
                  a[k++] = r[j++];
              }
          }
          while (i < left) {
              a[k++] = l[i++];
          }
          while (j < right) {
              a[k++] = r[j++];
          }
      }
}
