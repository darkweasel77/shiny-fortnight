package org.example

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class MainTest : StringSpec() {
    init {
        "handles date in format dd/mm/yyyy"  {
            val input = "[2018-04-19 12:58:59.272000] First='Oliver' Last='Watkins' Gender='1' Identifier='3C5FA0A7F44A928C' DOB='07/22/1948     ' PCP_NPI='111111111' PCP_Name='Howell, Sarah' TIN='121212121' TIN_Name='Local Care MDs' ApplyMonth='201801' LOB='M' Address1='7259 Oakland St. ' Address2='' City='Valley Stream' State='NY' Zip='11580' Phone1='(516) 465-1455' \n"
            val expected = "[2018-04-19 12:58:59.272000] First='Oliver' Last='Watkins' Gender='1' Identifier='3C5FA0A7F44A928C' DOB='X/X/1948' PCP_NPI='111111111' PCP_Name='Howell, Sarah' TIN='121212121' TIN_Name='Local Care MDs' ApplyMonth='201801' LOB='M' Address1='7259 Oakland St. ' Address2='' City='Valley Stream' State='NY' Zip='11580' Phone1='(516) 465-1455' \n"
            val actual = processLine(input)
            actual shouldBe expected
        }

        "handles date in format d/mm/yyyy" {
            val input = "[2018-04-19 12:58:59.272000] First='Oliver' Last='Watkins' Gender='1' Identifier='3C5FA0A7F44A928C' DOB='7/22/1948     ' PCP_NPI='111111111' PCP_Name='Howell, Sarah' TIN='121212121' TIN_Name='Local Care MDs' ApplyMonth='201801' LOB='M' Address1='7259 Oakland St. ' Address2='' City='Valley Stream' State='NY' Zip='11580' Phone1='(516) 465-1455' \n"
            val expected = "[2018-04-19 12:58:59.272000] First='Oliver' Last='Watkins' Gender='1' Identifier='3C5FA0A7F44A928C' DOB='X/X/1948' PCP_NPI='111111111' PCP_Name='Howell, Sarah' TIN='121212121' TIN_Name='Local Care MDs' ApplyMonth='201801' LOB='M' Address1='7259 Oakland St. ' Address2='' City='Valley Stream' State='NY' Zip='11580' Phone1='(516) 465-1455' \n"
            val actual = processLine(input)
            actual shouldBe expected
        }

        "handles date in format d/m/yyyy" {
            val input = "[2018-04-19 12:58:59.272000] First='Oliver' Last='Watkins' Gender='1' Identifier='3C5FA0A7F44A928C' DOB='7/2/1948' PCP_NPI='111111111' PCP_Name='Howell, Sarah' TIN='121212121' TIN_Name='Local Care MDs' ApplyMonth='201801' LOB='M' Address1='7259 Oakland St. ' Address2='' City='Valley Stream' State='NY' Zip='11580' Phone1='(516) 465-1455' \n"
            val expected = "[2018-04-19 12:58:59.272000] First='Oliver' Last='Watkins' Gender='1' Identifier='3C5FA0A7F44A928C' DOB='X/X/1948' PCP_NPI='111111111' PCP_Name='Howell, Sarah' TIN='121212121' TIN_Name='Local Care MDs' ApplyMonth='201801' LOB='M' Address1='7259 Oakland St. ' Address2='' City='Valley Stream' State='NY' Zip='11580' Phone1='(516) 465-1455' \n"
            val actual = processLine(input)
            actual shouldBe expected
        }

        "handles date in format d-m-yyyy" {
            val input = "[2018-04-19 12:58:59.272000] First='Oliver' Last='Watkins' Gender='1' Identifier='3C5FA0A7F44A928C' DOB='7-22-1948     ' PCP_NPI='111111111' PCP_Name='Howell, Sarah' TIN='121212121' TIN_Name='Local Care MDs' ApplyMonth='201801' LOB='M' Address1='7259 Oakland St. ' Address2='' City='Valley Stream' State='NY' Zip='11580' Phone1='(516) 465-1455' \n"
            val expected = "[2018-04-19 12:58:59.272000] First='Oliver' Last='Watkins' Gender='1' Identifier='3C5FA0A7F44A928C' DOB='X/X/1948' PCP_NPI='111111111' PCP_Name='Howell, Sarah' TIN='121212121' TIN_Name='Local Care MDs' ApplyMonth='201801' LOB='M' Address1='7259 Oakland St. ' Address2='' City='Valley Stream' State='NY' Zip='11580' Phone1='(516) 465-1455' \n"
            val actual = processLine(input)
            actual shouldBe expected
        }

        "doesn't do anything without dob" {
            val input = "[2018-04-19 12:58:59.272000] First='Oliver' Last='Watkins' Gender='1' Identifier='3C5FA0A7F44A928C' PCP_NPI='111111111' PCP_Name='Howell, Sarah' TIN='121212121' TIN_Name='Local Care MDs' ApplyMonth='201801' LOB='M' Address1='7259 Oakland St. ' Address2='' City='Valley Stream' State='NY' Zip='11580' Phone1='(516) 465-1455' \n"
            val expected = "[2018-04-19 12:58:59.272000] First='Oliver' Last='Watkins' Gender='1' Identifier='3C5FA0A7F44A928C' PCP_NPI='111111111' PCP_Name='Howell, Sarah' TIN='121212121' TIN_Name='Local Care MDs' ApplyMonth='201801' LOB='M' Address1='7259 Oakland St. ' Address2='' City='Valley Stream' State='NY' Zip='11580' Phone1='(516) 465-1455' \n"
            val actual = processLine(input)
            actual shouldBe expected
        }

        "doesn't barf on garbage" {
            val input = "46.105.14.53 - - [17/May/2015:10:05:44 +0000] \"GET /blog/tags/puppet?flav=rss20 HTTP/1.1\" 200 14872 \"-\" \"UniversalFeedParser/4.2-pre-314-svn +http://feedparser.org/\"\n"
            val expected = "46.105.14.53 - - [17/May/2015:10:05:44 +0000] \"GET /blog/tags/puppet?flav=rss20 HTTP/1.1\" 200 14872 \"-\" \"UniversalFeedParser/4.2-pre-314-svn +http://feedparser.org/\"\n"
            val actual = processLine(input)
            actual shouldBe expected
        }

        "doesn't barf on empty" {
            val input = ""
            val expected = ""
            val actual = processLine(input)
            actual shouldBe expected
        }
    }
}