package raicom.CAIP2024本科组国赛;

import java.util.Scanner;

public class RC_u5 {

    // 使用一个简单的 Record (或 class) 来封装一次移动的所有信息，使代码更清晰
    record Move(int r1, int c1, int r2, int c2, long score) {
    }

    private int N;
    private int[][] board;
    private long totalScore = 0;

    public RC_u5(int n, int[][] initialBoard) {
        this.N = n;
        this.board = initialBoard;
    }

    /**
     * 主游戏循环
     */
    public void play() {
        while (true) {
            Move bestMove = findBestMove();

            // 如果找不到得分大于0的移动，游戏结束
            if (bestMove == null || bestMove.score() <= 0) {
                break;
            }

            // 执行移动
            System.out.printf("(%d, %d) (%d, %d) %d\n",
                    bestMove.r1, bestMove.c1, bestMove.r2, bestMove.c2, bestMove.score);
            totalScore += bestMove.score;
            updateBoard(bestMove);
        }
        System.out.println(totalScore);
    }

    /**
     * 寻找当前面板上的最佳移动
     * 
     * @return 返回最佳移动对象，如果没有有效移动则返回 null
     */
    private Move findBestMove() {
        // 创建二维前缀和数组
        long[][] scoreSum = new long[N + 1][N + 1];
        int[][] blackHoleSum = new int[N + 1][N + 1];

        // 计算前缀和
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                int score = board[i - 1][j - 1];
                int isBlackHole = (score == 0) ? 1 : 0;
                scoreSum[i][j] = score + scoreSum[i - 1][j] + scoreSum[i][j - 1] - scoreSum[i - 1][j - 1];
                blackHoleSum[i][j] = isBlackHole + blackHoleSum[i - 1][j] + blackHoleSum[i][j - 1]
                        - blackHoleSum[i - 1][j - 1];
            }
        }

        Move bestMove = null;

        // 四层循环遍历所有可能的矩形
        for (int r1 = 1; r1 <= N; r1++) {
            for (int c1 = 1; c1 <= N; c1++) {
                for (int r2 = r1; r2 <= N; r2++) {
                    for (int c2 = c1; c2 <= N; c2++) {
                        // 使用前缀和 O(1) 检查黑洞
                        int holes = blackHoleSum[r2][c2] - blackHoleSum[r1 - 1][c2] - blackHoleSum[r2][c1 - 1]
                                + blackHoleSum[r1 - 1][c1 - 1];
                        if (holes > 0) {
                            continue; // 矩形内有黑洞，无效
                        }

                        // 使用前缀和 O(1) 计算得分
                        long currentScore = scoreSum[r2][c2] - scoreSum[r1 - 1][c2] - scoreSum[r2][c1 - 1]
                                + scoreSum[r1 - 1][c1 - 1];

                        // 根据规则更新最佳移动
                        if (bestMove == null || currentScore > bestMove.score) {
                            bestMove = new Move(r1, c1, r2, c2, currentScore);
                        }
                    }
                }
            }
        }
        return bestMove;
    }

    /**
     * 根据给定的移动更新游戏面板（模拟方块下落）
     * 
     * @param move 已执行的移动
     */
    private void updateBoard(Move move) {
        // 对矩形内的每一列进行处理
        for (int c = move.c1; c <= move.c2; c++) {
            // 使用一个 write 指针指向要填充的最高行
            int writeRow = move.r2;
            // 从矩形区域上方开始，将方块向下移动
            for (int r = move.r1 - 1; r >= 1; r--) {
                board[writeRow - 1][c - 1] = board[r - 1][c - 1];
                writeRow--;
            }
            // 将顶部剩余的空位填充为黑洞
            while (writeRow >= 1) {
                board[writeRow - 1][c - 1] = 0;
                writeRow--;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] initialBoard = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                initialBoard[i][j] = sc.nextInt();
            }
        }
        sc.close();

        RC_u5 game = new RC_u5(N, initialBoard);
        game.play();
    }
}