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
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

WebUI.callTestCase(findTestCase('Reusable Testcase/Open and login app'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Homepage/btn_burgermenu'), 0)

Mobile.tap(findTestObject('Homepage/btn_payment'), 0)

Mobile.verifyElementVisible(findTestObject('Payment/text_payment'), 0)

Mobile.verifyElementVisible(findTestObject('Payment/text_accounttopay'), 0)

Mobile.verifyElementVisible(findTestObject('Payment/text_selectapayee'), 0)

Mobile.verifyElementVisible(findTestObject('Payment/btn_dropdownaccountpay'), 0)

Mobile.verifyElementVisible(findTestObject('Payment/btn_dropdownpayee'), 0)

Mobile.verifyElementVisible(findTestObject('Payment/textbox_amountpay'), 0)

Mobile.verifyElementVisible(findTestObject('Payment/btn_makepayment'), 0)

Mobile.tap(findTestObject('Payment/btn_dropdownaccountpay'), 0)

//get global variable for searching by label
String account = GlobalVariable.Global_accountbri

String amount = GlobalVariable.Global_amountbri

Double amountDouble = Double.parseDouble(amount)

// Create global variable C by combining A and B
String findlabel = ((account + ' ($') + amount) + ')'

Mobile.selectListItemByLabel(findTestObject('Payment/dropdown_accounttopay'), findlabel, 0)

Mobile.tap(findTestObject('Payment/btn_dropdownpayee'), 0)

Mobile.selectListItemByLabel(findTestObject('Payment/dropdown_payee'), 'food (P3)', 0)

Mobile.setText(findTestObject('Payment/textbox_amountpay'), '999999999999999999', 0)

Mobile.tap(findTestObject('Payment/btn_makepayment'), 0)

//The payment should fail, and the amount should remain unchanged
double newAmount = amountDouble - 0.00

String newAmountString = String.format('%.2f', newAmount)

GlobalVariable.Global_amountbri = newAmountString

Mobile.tap(findTestObject('Homepage/btn_burgermenu'), 0)

Mobile.tap(findTestObject('Homepage/btn_menuaccounts'), 0)

Mobile.verifyElementVisible(findTestObject('Accounts/text_accounts'), 0)

//check new amount
def bri = Mobile.getText(findTestObject('Accounts/text_amountbri'), 0)

String stringBri = bri

def amountbri = stringBri =~ '\\$([\\d.,]+)'

if (amountbri.find()) {
    String extractedAmountBri = amountbri.group(1)

    if (extractedAmountBri == GlobalVariable.Global_amountbri) {
            KeywordUtil.markPassed('SUCCESS')
    } else {
        KeywordUtil.markFailed('Failed')
    }
    
    println("CHECK SAME VALUE $extractedAmountBri $GlobalVariable.Global_amountbri")
}

WebUI.callTestCase(findTestCase('Reusable Testcase/Close app'), [:], FailureHandling.STOP_ON_FAILURE)

