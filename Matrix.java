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
