import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

Mobile.startExistingApplication(GlobalVariable.Global_packageapp, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Login/textbox_username'), 0)

Mobile.verifyElementVisible(findTestObject('Login/textbox_password'), 0)

Mobile.verifyElementVisible(findTestObject('Login/btn_login'), 0)

Mobile.setText(findTestObject('Login/textbox_username'), GlobalVariable.Global_username, 0)

Mobile.setText(findTestObject('Login/textbox_password'), GlobalVariable.Global_password, 0)

Mobile.tap(findTestObject('Login/btn_login'), 0)

Mobile.waitForElementPresent(findTestObject('Homepage/text_dashboard'), 0)

Mobile.verifyElementVisible(findTestObject('Homepage/text_dashboard'), 0)

//Define amount each account to global variable
Mobile.tap(findTestObject('Homepage/btn_burgermenu'), 0)

Mobile.tap(findTestObject('Homepage/btn_menuaccounts'), 0)

Mobile.verifyElementVisible(findTestObject('Accounts/text_amountbca'), 0)

Mobile.verifyElementVisible(findTestObject('Accounts/text_amountbri'), 0)

Mobile.verifyElementVisible(findTestObject('Accounts/text_amountbni'), 0)

def bca = Mobile.getText(findTestObject('Accounts/text_amountbca'), 0)

def bri = Mobile.getText(findTestObject('Accounts/text_amountbri'), 0)

def bni = Mobile.getText(findTestObject('Accounts/text_amountbni'), 0)

// Define the input text
String textbca = bca

String textbri = bri

String textbni = bni

// Use a regular expression to extract the amount 
def amountbca = textbca =~ '\\$([\\d.,]+)'

def amountbri = textbri =~ '\\$([\\d.,]+)'

def amountbni = textbni =~ '\\$([\\d.,]+)'

//String dollarbca = (inputText =~ /\$\d+\.\d{2}/)[0]
if (amountbca.find()) {
    String extractedAmountBca = amountbca.group(1)

    GlobalVariable.Global_amountbca = extractedAmountBca
}

if (amountbri.find()) {
    String extractedAmountBri = amountbri.group(1)

    GlobalVariable.Global_amountbri = extractedAmountBri
}

if (amountbni.find()) {
    String extractedAmountBni = amountbni.group(1)

    GlobalVariable.Global_amountbni = extractedAmountBni
}

