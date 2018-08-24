package com.guardanis.fontutils.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.guardanis.fontutils.fonts.FontUtils

class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstance: Bundle?) {
        super.onCreate(savedInstance)

        setContentView(R.layout.activity_main)

        val titleToReplace = findViewById<TextView>(R.id.main__text_replacement)

        FontUtils.getInstance(this)
                .setTypeface(titleToReplace, R.string.font_family__hybrid, exampleCustomFontStyleId)
    }

    companion object {

        val exampleCustomFontStyleId = 3337
    }
}