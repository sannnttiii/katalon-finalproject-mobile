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

Mobile.tap(findTestObject('Homepage/btn_maketransfer'), 0)

Mobile.verifyElementVisible(findTestObject('Transfer/text_transfer'), 0)

Mobile.verifyElementVisible(findTestObject('Transfer/text_sendingaccount'), 0)

Mobile.verifyElementVisible(findTestObject('Transfer/text_receivingaccount'), 0)

Mobile.verifyElementVisible(findTestObject('Transfer/textbox_transferamount'), 0)

Mobile.verifyElementVisible(findTestObject('Transfer/btn_confirmtransfer'), 0)

Mobile.tap(findTestObject('Transfer/btn_dropdownsend'), 0)

//get global variable for searching by label
String sendaccount = GlobalVariable.Global_accountbca

String sendamount = GlobalVariable.Global_amountbca

Double amountDoubleSend = Double.parseDouble(sendamount)

// Create global variable C by combining A and B
String findlabelsend = ((sendaccount + ' ($') + sendamount) + ')'

Mobile.selectListItemByLabel(findTestObject('Transfer/dropdown_sendingaccount'), findlabelsend, 0)

Mobile.setText(findTestObject('Transfer/textbox_transferamount'), '500', 0)

Mobile.tap(findTestObject('Transfer/btn_dropdownreceive'), 0)

String receiveaccount = GlobalVariable.Global_accountbni

String receiveamount = GlobalVariable.Global_amountbni

Double amountDoubleReceive = Double.parseDouble(receiveamount)

// Create global variable C by combining A and B
String findlabelreceive = ((receiveaccount + ' ($') + receiveamount) + ')'

Mobile.selectListItemByLabel(findTestObject('Transfer/dropdown_receivingaccount'), findlabelreceive, 0)

Mobile.tap(findTestObject('Transfer/btn_confirmtransfer'), 0)

double newAmountSend = amountDoubleSend - 500.00

double newAmountReceive = amountDoubleReceive + 500.00

String newAmountStringSend = String.format('%.2f', newAmountSend)

String newAmountStringReceive = String.format('%.2f', newAmountReceive)

println("NEW AMOUNT STRING $newAmountStringSend $newAmountStringReceive")

GlobalVariable.Global_amountbca = newAmountStringSend

GlobalVariable.Global_amountbni = newAmountStringReceive

Mobile.tap(findTestObject('Homepage/btn_burgermenu'), 0)

Mobile.tap(findTestObject('Homepage/btn_menuaccounts'), 0)

Mobile.verifyElementVisible(findTestObject('Accounts/text_accounts'), 0)

//check new amount
def bca = Mobile.getText(findTestObject('Accounts/text_amountbca'), 0)

def bni = Mobile.getText(findTestObject('Accounts/text_amountbni'), 0)

String stringBca = bca

String stringBni = bni

def amountbca = stringBca =~ '\\$([\\d.,]+)'

def amountbni = stringBni =~ '\\$([\\d.,]+)'

if (amountbca.find() && amountbni.find()) {
    String extractedAmountBca = amountbca.group(1)

    String extractedAmountBni = amountbni.group(1)

    if ((extractedAmountBca == GlobalVariable.Global_amountbca) && (extractedAmountBni == GlobalVariable.Global_amountbni)) {
        Mobile.tap(findTestObject('Accounts/btn_accountbca'), 0)

        Mobile.verifyElementVisible(findTestObject('Accounts/Transactions/text_transactions'), 0)

        Mobile.tap(findTestObject('Accounts/Transactions/btn_orderby'), 0)

        Mobile.selectListItemByLabel(findTestObject('Accounts/Transactions/dropdown_orderby'), 'New - Old', 0)

        Mobile.verifyElementVisible(findTestObject('Accounts/Transactions/text_namedetailtransaction'), 0)

        def name = Mobile.getText(findTestObject('Accounts/Transactions/text_namedetailtransaction'), 0)

        Mobile.verifyElementVisible(findTestObject('Accounts/Transactions/text_amountdetailtransferpayment'), 0)

        def amountdetail = Mobile.getText(findTestObject('Accounts/Transactions/text_amountdetailtransferpayment'), 0)

        println("NAME AND AMOUNT DETAIL $name $amountdetail")

        if (name.toLowerCase().contains('transfer') && amountdetail.toLowerCase().contains('$500.00')) {
            Mobile.pressBack()

            Mobile.tap(findTestObject('Accounts/btn_accountbni'), 0)

            Mobile.verifyElementVisible(findTestObject('Accounts/Transactions/text_transactions'), 0)

            Mobile.tap(findTestObject('Accounts/Transactions/btn_orderby'), 0)

            Mobile.selectListItemByLabel(findTestObject('Accounts/Transactions/dropdown_orderby'), 'New - Old', 0)

            Mobile.verifyElementVisible(findTestObject('Accounts/Transactions/text_namedetailtransaction'), 0)

            def namebni = Mobile.getText(findTestObject('Accounts/Transactions/text_namedetailtransaction'), 0)

            Mobile.verifyElementVisible(findTestObject('Accounts/Transactions/text_amountdetailtransferpayment'), 0)

            def amountdetailbni = Mobile.getText(findTestObject('Accounts/Transactions/text_amountdetailtransferpayment'), 
                0)

            if (namebni.toLowerCase().contains('transfer') && amountdetailbni.toLowerCase().contains('$500.00')) {
                KeywordUtil.markPassed('SUCCESS')
            } else {
                KeywordUtil.markFailed('Failed')
            }
        } else {
            KeywordUtil.markFailed('Failed')
        }
    } else {
        KeywordUtil.markFailed('Failed')
    }
    
    println("CHECK SAME VALUE $extractedAmountBni $GlobalVariable.Global_amountbni $extractedAmountBca $GlobalVariable.Global_amountbca")
}

WebUI.callTestCase(findTestCase('Reusable Testcase/Close app'), [:], FailureHandling.STOP_ON_FAILURE)

