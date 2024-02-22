/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package at.ac.fhcampuswien
import kotlin.random.Random

class App {
    // Game logic for a number guessing game
    fun playNumberGame(digitsToGuess: Int = 4) {
        //TODO: build a menu which calls the functions and works with the return values
        val generatedNumber = generateRandomNonRepeatingNumber(digitsToGuess)
        var attempts = 0

        println("Generated number: $generatedNumber")
        while (true) {
            print("User input: ")
            val userInput = readLine()?.toIntOrNull()

            if (userInput != null) {
                val compareResult = checkUserInputAgainstGeneratedNumber(userInput, generatedNumber)
                println(compareResult)

                if (compareResult.m == digitsToGuess) {
                    println("Congratulations! You guessed the correct number in $attempts attempts.")
                    break
                }
            } else {
                println("Invalid input. Please enter a valid number.")
            }

            attempts++
        }
    }

    /**
     * Generates a non-repeating number of a specified length between 1-9.
     *
     * Note: The function is designed to generate a number where each digit is unique and does not repeat.
     * It is important to ensure that the length parameter does not exceed the maximum possible length
     * for non-repeating digits (which is 9 excluding 0 for base-10 numbers).
     *
     * @param length The length of the non-repeating number to be generated.
     *               This dictates how many digits the generated number will have.
     * @return An integer of generated non-repeating number.
     *         The generated number will have a number of digits equal to the specified length and will
     *         contain unique, non-repeating digits.
     * @throws IllegalArgumentException if the length is more than 9 or less than 1.
     */
    val generateRandomNonRepeatingNumber: (Int) -> Int = { length ->
        //TODO implement the function
        if (length < 1 || length > 9) {
            throw IllegalArgumentException("Invalid length. Length must be between 1 and 9.")
        }

        val digits = (1..9).shuffled().take(length)
        var result = 0

        for (digit in digits) {
            result = result * 10 + digit
        }

        result
    }

    /**
     * Compares the user's input integer against a generated number for a guessing game.
     * This function evaluates how many digits the user guessed correctly and how many of those
     * are in the correct position. The game generates number with non-repeating digits.
     *
     * Note: The input and the generated number must both be numbers.
     * If the inputs do not meet these criteria, an IllegalArgumentException is thrown.
     *
     * @param input The user's input integer. It should be a number with non-repeating digits.
     * @param generatedNumber The generated number with non-repeating digits to compare against.
     * @return [CompareResult] with two properties:
     *         1. `n`: The number of digits guessed correctly (regardless of their position).
     *         2. `m`: The number of digits guessed correctly and in the correct position.
     *         The result is formatted as "Output: m:n", where "m" and "n" represent the above values, respectively.
     * @throws IllegalArgumentException if the inputs do not have the same number of digits.
     */
    val checkUserInputAgainstGeneratedNumber: (Int, Int) -> CompareResult = { input, generatedNumber ->
        //TODO implement the function
        val inputStr = input.toString()
        val generatedStr = generatedNumber.toString()

        if (inputStr.length != generatedStr.length) {
            throw IllegalArgumentException("Input and generated number must have the same number of digits.")
        }

        val correctDigits = inputStr.toSet().intersect(generatedStr.toSet()).size
        val correctPosition = inputStr.indices.count { inputStr[it] == generatedStr[it] }

        CompareResult(correctDigits, correctPosition)

    }
}

fun main() {
    println("Hello World!")
    // TODO: call the App.playNumberGame function with and without default arguments
    App().playNumberGame()
}
