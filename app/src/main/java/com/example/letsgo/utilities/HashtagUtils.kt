package com.example.letsgo.utilities

object HashtagUtils {

    // given input string "Root/Parent/Child", this function returns "Child"
    fun getLowest(hashtag: String) : String{

        var lowest: String = ""
        var index =  hashtag.length - 1

        // while iterator is within range (0 <= i < hashtag.length)
        while(index >= 0){
            // if current character is a slash, break
            if(hashtag.get(index) == '/')
                break

            // otherwise
            lowest = hashtag.get(index) + lowest
            index--
        }

        return lowest

    }

    // given input string "Root/Parent/Child", this function returns "Root/Parent"
    fun getPath(hashtag: String) : String{

        var index =  hashtag.length - 1

        // find last slash character in string
        while(index >= 0){
            if(hashtag.get(index) == '/')
                break
            index--
        }

        // if this is -1 or 0, no parent was found
            // if index == -1, the string does not contain a slash
            // if index == 0, the first character is a slash
        if(index <= 0)
            throw StringIndexOutOfBoundsException()

        // return string up until the last slash
        return hashtag.substring(0, index)

    }

    // given input string "Root/Parent/Child", this function returns "Parent"
    fun getParent(hashtag: String) : String{

        return getLowest(getPath(hashtag))

    }

}