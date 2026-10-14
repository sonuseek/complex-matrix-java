public class Matrix {
    private final int rows;
    private final int cols;
    private final Complex[][] data;

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.data = new Complex[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.data[i][j] = Complex.ZERO;
            }
        }
    }


    public Complex get(int i, int j) {
        return data[i][j];
    }


    public void set(int i, int j, Complex value) {
        data[i][j] = value;
    }

    public boolean isSquare() {
        return rows == cols;
    }


    public Matrix add(Matrix b) {
        if(this.rows != b.rows || this.cols != b.cols){
            throw new IllegalArgumentException("Размеры матриц не совпадают для сложения");
        }

        Matrix res = new Matrix(this.rows, this.cols);

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                res.data[i][j] = this.data[i][j].add(b.data[i][j]);
            }
        }
        return res;
    }


    public Matrix sub(Matrix b) {
        if(this.rows != b.rows || this.cols != b.cols){
            throw new IllegalArgumentException("Размеры матриц не совпадают для вычитания");
        }

        Matrix res = new Matrix(this.rows, this.cols);

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                res.data[i][j] = this.data[i][j].sub(b.data[i][j]);
            }
        }
        return res;
    }



    public Matrix mul(Matrix b) {
        if(this.cols != b.rows) {
            throw new IllegalArgumentException("Размеры матриц не подходят для умножения");
        }

        Matrix res = new Matrix(this.rows, b.cols);

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < b.cols; j++) {

                Complex sum = new Complex(0, 0);
                for (int z = 0; z < this.cols; z++) {
                    sum = sum.add(this.data[i][z].mul(b.data[z][j]));
                }

                res.data[i][j] = sum;
            }
        }
        return res;
    }

    public Matrix div(Matrix b) {
        return this.mul(b.inverse());
    }


    public Matrix transpose() {
        Matrix res = new Matrix(cols, rows);
        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j ++) {
                res.data[j][i] = this.data[i][j];
            }
        }
        return res;
    }

    public Complex determinant(){
        if (this.rows != this.cols) {
            throw new IllegalArgumentException("Размер матрицы не подходит для нахождения определителя");
        }

        Complex[][] temp = new Complex[rows][];

        for (int i = 0; i < rows; i++) {
            temp[i] = new Complex[rows];
            for (int j = 0; j < rows; j++) {
                temp[i][j] = data[i][j];
            }
        }

        Complex det = Complex.ONE;
        int sign = 1;
        
        for (int col = 0; col < rows; col++) {
            int pivot = -1;
            for (int k = col; k < rows; k++) {
                if (!temp[k][col].isZero()) {
                    pivot = k;
                    break;
                }
            }

            if (pivot == -1) {
                return Complex.ZERO;
            }

            if (pivot != col) {
                Complex[] tmp = temp[pivot];
                temp[pivot] = temp[col];
                temp[col] = tmp;
                sign = -sign;
            }
            
            det = det.mul(temp[col][col]);

            for (int r = col + 1; r < rows; r++) {
                Complex factor = temp[r][col].div(temp[col][col]);
                for (int c = col; c < rows; c++) {  
                    temp[r][c] = temp[r][c].sub(factor.mul(temp[col][c]));
                }
            }
        }

        if (sign == -1) {
            det = new Complex(-det.real(), -det.imaginary());
        }
        return det;

    }


    public Matrix inverse() {
        if (!isSquare()) {
            throw new IllegalArgumentException("Размер матрицы не подходит для нахождения обратной матрицы");
        }

        Complex[][] temp = new Complex[rows][2 * rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                temp[i][j] = data[i][j];
                temp[i][rows + j] = (i == j) ? Complex.ONE : Complex.ZERO;
            }
        }

        for (int col = 0; col < rows; col++) {
            int pivot = -1;
            for (int k = col; k < rows; k++) {
                if (!temp[k][col].isZero()) {
                    pivot = k;
                    break;
                }
            }

            if (pivot == -1) {
                throw new ArithmeticException("Матрица вырождена, обратной не существует");
            }
        
            if (pivot != col) {
                Complex[] tmp = temp[pivot];
                temp[pivot] = temp[col];
                temp[col] = tmp;
            }

            Complex pivotValue = temp[col][col];
            for (int c = 0; c < 2 * rows; c++) {
                temp[col][c] = temp[col][c].div(pivotValue);
            }

            for (int r = 0; r < rows; r++) {
                if (r == col) continue;

                Complex factor = temp[r][col];

                if (factor.isZero()) continue;

                for (int c = 0; c < 2 * rows; c++) {
                    temp[r][c] = temp[r][c].sub(factor.mul(temp[col][c]));
                }
            }
        }

        Matrix result = new Matrix(rows, rows);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                result.set(i, j, temp[i][rows + j]);
            }
        }
        return result;
    }






    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            sb.append("[ ");
            for (int j = 0; j < cols; j++) {
                sb.append(data[i][j]).append("  ");
            }
            sb.append("]\n");
        }
        return sb.toString();
    }
}
