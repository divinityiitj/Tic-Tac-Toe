package com.example.tictactoe

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener{
    var PLAYER = true
    var TURN_COUNT=0
    var boardStatus = Array(3){IntArray(3)}
    lateinit var board:Array<Array<Button>>//2d array of buttons initialised
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        board = arrayOf(
            arrayOf(button1,button2,button3), /*array initialised with buttons */
            arrayOf(button4,button5,button6),
            arrayOf(button7,button8,button9)
        )
        for(i in board){
            for(button in i){
                button.setOnClickListener(this)
            }
        }
        initialiseBoardStatus()
        resetBtn.setOnClickListener{
                PLAYER =true
                TURN_COUNT=0
            displayTv.text="Player X turn"
                initialiseBoardStatus() /*getting back to state initial*/
        }

    }
    private fun initialiseBoardStatus(){
        for(i in 0..2){
            for(j in 0..2){
                boardStatus[i][j]=-1
                //board[i][j].isEnabled =true
                //board[i][j].text=""
            }
        }
        for(i in board){
            for(button in i){
                button.isEnabled =true
                button.text=""
            }
        }
    }

    override fun onClick(view:View) {
        when(view.id){
            R.id.button1 ->{    //now we handle the button clicks here
                updateValue(row=0,col=0,player=PLAYER)
            }
            R.id.button2 ->{
                updateValue(row=0,col=1,player=PLAYER)
            }
            R.id.button3 ->{
                updateValue(row=0,col=2,player=PLAYER)
            }
            R.id.button4 ->{
                updateValue(row=1,col=0,player=PLAYER)
            }
            R.id.button5 ->{
                updateValue(row=1,col=1,player=PLAYER)
            }
            R.id.button6 ->{
                updateValue(row=1,col=2,player=PLAYER)
            }
            R.id.button7 ->{
                updateValue(row=2,col=0,player=PLAYER)
            }
            R.id.button8->{
                updateValue(row=2,col=1,player=PLAYER)
            }
            R.id.button9 ->{
                updateValue(row=2,col=2,player=PLAYER)
            }
        }
        TURN_COUNT++
        PLAYER=!PLAYER //will inverse it for switching between X and O
        if(PLAYER){
            updateDisplay("Player X turn")
        }
        else{
            updateDisplay("Player Y turn")
        }
        if(TURN_COUNT==9){
            updateDisplay("Game Draw")
        }
        checkWinner()
    }

    private fun checkWinner() {
        //horizontal
        for(i in 0..2){
            if(boardStatus[i][0]==boardStatus[i][1] && boardStatus[i][0]==boardStatus[i][2]){
                if(boardStatus[i][0]==1){
                    updateDisplay("Player X is Winner")
                    break
                }
                else if(boardStatus[i][0]==0){
                    updateDisplay("Player Y is Winner")
                    break
                }
            }

        }
        //vertical
        for(i in 0..2){
            if(boardStatus[0][i]==boardStatus[1][i] && boardStatus[0][i]==boardStatus[2][i]){
                if(boardStatus[0][i]==1){
                    updateDisplay("Player X is Winner")
                    break
                }
                else if(boardStatus[0][i]==0){
                    updateDisplay("Player Y is Winner")
                    break
                }
            }

        }
        //diagonal
        if(boardStatus[0][0]==boardStatus[1][1] && boardStatus[0][0]==boardStatus[2][2]){
            if(boardStatus[0][0]==1){
                updateDisplay("Player X is Winner")

            }
            else if(boardStatus[0][0]==0){
                updateDisplay("Player Y is Winner")

            }
        }
        //2st diagonal
        if(boardStatus[0][2]==boardStatus[1][1] && boardStatus[0][2]==boardStatus[2][0]){
            if(boardStatus[0][2]==1){
                updateDisplay("Player X is Winner")

            }
            else if(boardStatus[0][2]==0){
                updateDisplay("Player Y is Winner")

            }
        }

    }

    private fun updateDisplay(text: String) {
        displayTv.text = text
        if(text.contains("Winner")) {
            //if text has some part winner then disable buttons
            disableButton()
        }
    }
    private fun disableButton(){
        for(i in board){
            for(button in i){
                button.isEnabled =false
            }
        }
    }

    private fun updateValue(row: Int, col: Int, player: Boolean) {
        val text = if(player) "X" else "O" //if true i.e. player 1 is X then buton will show X else O
        val value = if(player) 1 else 0
            board[row][col].apply {
                isEnabled = false //if a btn clicked then we cant click again
                setText(text)

            }
        boardStatus[row][col]=value
    }
}