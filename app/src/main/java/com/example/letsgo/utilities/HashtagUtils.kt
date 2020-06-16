package com.example.letsgo.utilities

object HashtagUtils {

    // given input string "Root/Parent/Child", this function returns "Child"
    fun getLowest(hashtag: String) : String {

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
    fun getPath(hashtag: String) : String {

        var index =  hashtag.length - 1

        // find last slash character in string
        while(index >= 0){
            if(hashtag.get(index) == '/')
                break
            index--
        }

        // if index is -1, no parent was found because there are no slashes
        if(index < 0)
            return ""

        // if index is 0, no parent was found because the first character is a slash
        if(index == 0 || index == hashtag.length - 1)
            throw StringIndexOutOfBoundsException()


        // return string up until the last slash
        return hashtag.substring(0, index)

    }

    fun hasParent(hashtag: String) : Boolean {

        return !getPath(hashtag).equals("")

    }

    // given input string "Root/Parent/Child", this function returns "Parent"
    // POSSIBLY NOT NEEDED
    //fun getParent(hashtag: String) : String{

    //    return getLowest(getPath(hashtag))

    //}

    // checks whether a given string is a valid hashtag
    fun verifyHashtag(hashtag: String) : Boolean {

        // if hashtag length is less than 1 (empty string), INVALID
        if(hashtag.length < 1)
            return false

        // if first or last character is a slash, INVALID
        if(hashtag[0] == '/' || hashtag[hashtag.length - 1] == '/')
            return false

        return true

    }

}