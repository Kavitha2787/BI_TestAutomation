using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Configuration;
using System.IO;


namespace UserConfiguration
{
    public partial class frm_UserConfiguration : Form
    {
        public object ConfigurationManager { get; private set; }

        //User Variables
        //private static String UserTestxmlfile;
        TestNgXmlParser testNgXmlParser;
        DataTable dt = new DataTable();
        String hhrr = "";
        String datamodename = "";
        String datamodeno = "";
        String browser = "";

        String modelConfigFile;
        String modelExecutableFile;
        String modelTestXMLFile;

        String userfoldernameformat;
        String userfilenameformat;


        String userConfigFile;
        String userExecutableFile;
        String userTestXMLFile;




        String txtFromDialog;
        int currentrowindex;
        String currentcombovalue="";



        public frm_UserConfiguration()
        {
            InitializeComponent();
        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void label4_Click(object sender, EventArgs e)
        {

        }

        private void frm_UserConfiguration_Load(object sender, EventArgs e)
        {
            BindBrowserInCombo();
        }


        private void BindBrowserInCombo()
        {
            string[] mykey = System.Configuration.ConfigurationManager.AppSettings["Browser"].Split(',');
            for (int i = 0; i < mykey.Length; i++)
            {
                cmb_Browser.Items.Add(mykey[i]);
            }
        }

        private void btn_Load_Click(object sender, EventArgs e)
        {
            if (this.txt_HHRR.Text == "" || this.txt_DataModeName.Text == "" || this.txt_DataModeNo.Text == "" || this.cmb_Browser.Text == "")
            {
                MessageBox.Show("Select Browser Or Enter Some Text in HHRR,DatamodeID and DatamodeNumber Textbox");
                return;
            }
            else
            {
                PrepareVariables();
                LoadCmbTest();
                LoadGrdvwProperties();
                LoadGrdvwMethods();

                this.cmb_Browser.Enabled = false;
                this.txt_HHRR.Enabled = false;
                this.txt_DataModeName.Enabled = false;
                this.txt_DataModeNo.Enabled = false;
                this.btn_Load.Enabled = false;
            }

                        
        }
        

        private void PrepareVariables()
        {
            String root = Directory.GetCurrentDirectory();
            String parent = Path.GetDirectoryName(root);

            modelConfigFile = parent + "\\" + "System" + "\\" + System.Configuration.ConfigurationManager.AppSettings["ModelProperties"];
            modelExecutableFile = parent + "\\" + "System" + "\\" + System.Configuration.ConfigurationManager.AppSettings["MmodelBat"];
            modelTestXMLFile = parent + "\\" + "System" + "\\" + System.Configuration.ConfigurationManager.AppSettings["ModelXml"];

            hhrr = this.txt_HHRR.Text;
            datamodename = this.txt_DataModeName.Text;
            datamodeno = this.txt_DataModeNo.Text;
            browser = this.cmb_Browser.SelectedItem.ToString();

            userfoldernameformat = parent + "\\" + hhrr + '_' + datamodename + '_' + datamodeno;
            userfilenameformat = hhrr + '_' + datamodename + '_' + datamodeno;

            userConfigFile= userfoldernameformat+"\\"+ browser + "_" + userfilenameformat +".properties";
            userExecutableFile= userfoldernameformat + "\\" + browser + "_" + userfilenameformat + ".bat";
            userTestXMLFile= userfoldernameformat + "\\" + browser + "_" + userfilenameformat + ".xml";

        }



        public void LoadGrdvwProperties()
        {
            //Create the data table
            DataTable datatable_grdvw_Properties = new DataTable();
            datatable_grdvw_Properties.Columns.Add(new DataColumn("PropKey", System.Type.GetType("System.String")));
            datatable_grdvw_Properties.Columns.Add(new DataColumn("PropValue", System.Type.GetType("System.String")));
           
            DataRow row;
            try
            {
                StreamReader objStreamReader;
                objStreamReader = File.OpenText(this.PropetiesFileForReading);
                string[] lines = System.IO.File.ReadAllLines(this.PropetiesFileForReading);
                for (int i = 0; i < (lines.Length); i++)
                {
                    String delimiter = "=";
                    String tempstr = lines[i].ToString();
                    //lines[i].ToString();

                    int posn = tempstr.IndexOf(delimiter);
                    String propkey = tempstr.Substring(0, posn);
                    Console.WriteLine(tempstr);
                    int x = tempstr.Length - (posn + 1);
                    String propvalue = tempstr.Substring(posn + 1, x).Trim();

                    switch (propkey.ToUpper())
                    {
                        case "BROWSER":
                            break;
                        case "HHRR":
                            break;
                        case "DATAMODENAME":
                            break;
                        case "DATAMODENO":
                            break;
                        case "BROWSERTYPE":
                            break;
                        default:
                            row = datatable_grdvw_Properties.NewRow();
                            row["PropKey"] = propkey.ToString();
                            row["PropValue"] = propvalue.ToString();

                            datatable_grdvw_Properties.Rows.Add(row);
                            int index = datatable_grdvw_Properties.Rows.Count;
                            this.grdvw_Properties.AutoGenerateColumns = false;
                            this.grdvw_Properties.DataSource = datatable_grdvw_Properties;
                            this.grdvw_Properties.DefaultCellStyle.WrapMode = DataGridViewTriState.True;
                            if (propkey.StartsWith("#"))
                            {
                                this.grdvw_Properties.Rows[index - 1].Cells[1].ReadOnly = true;
                            }
                            break;
                    }
                }               
                this.grdvw_Properties_txtPropKey.DataPropertyName = "PropKey";
                this.grdvw_Properties_txtPropValue.DataPropertyName = "PropValue";
                objStreamReader.Close();
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex);
            }

        }

        public void LoadGrdvwMethods()
        {
            if (this.cmbTest.SelectedItem.ToString() != null || !String.IsNullOrWhiteSpace(this.cmbTest.SelectedItem.ToString()))
            {
                
                //Create the data table
                DataTable datatable_grdvw_methods = new DataTable();
                datatable_grdvw_methods.Columns.Add(new DataColumn("IncludeExclude", System.Type.GetType("System.Boolean")));
                datatable_grdvw_methods.Columns.Add(new DataColumn("MethodeName", System.Type.GetType("System.String")));
                DataRow row;


                String classname = this.cmbTest.SelectedItem.ToString();
                String[] allmethods = testNgXmlParser.GetAllMethodsByClassName(classname);
                for (int i = 0; i < (allmethods.Length); i++)
                {
                    String methodstate = testNgXmlParser.GetMethodStateByClass(classname, allmethods[i]);
                    row = datatable_grdvw_methods.NewRow();
                    if (methodstate == "include")
                    {
                        row["IncludeExclude"] = true;
                    }
                    else
                    {
                        row["IncludeExclude"] = false;
                    }
                    row["MethodeName"] = allmethods[i].ToString();
                    datatable_grdvw_methods.Rows.Add(row);
                    int index = datatable_grdvw_methods.Rows.Count;
                    this.grdvw_methods.AutoGenerateColumns = false;
                    this.grdvw_methods.DataSource = datatable_grdvw_methods;
                    this.grdvw_methods_chkboxinclude.DataPropertyName = "IncludeExclude";
                    this.grdvw_methods_txtMethods.DataPropertyName = "MethodeName";
                }

            }

        }
        private void LoadCmbTest()
        {
            testNgXmlParser = new TestNgXmlParser(this.TestXMLFileForReading);
            int length = testNgXmlParser.GetAllClassNames().Length;
            for (int i = 0; i < testNgXmlParser.GetAllClassNames().Length; i++)
            {
                this.cmbTest.Items.Add(testNgXmlParser.GetAllClassNames()[i]);
            }
            cmbTest.SelectedIndex=0;
        }
       
        private Boolean ValidateEmptyCellInDataGridView(System.Windows.Forms.DataGridView dataGridView)
        {
            Boolean retval = true;
            foreach (DataGridViewRow rw in dataGridView.Rows)
            {
                for (int i = 0; i < rw.Cells.Count; i++)
                {
                    if (rw.Cells[i].Value == null || rw.Cells[i].Value == DBNull.Value || String.IsNullOrWhiteSpace(rw.Cells[i].Value.ToString()))
                    {
                        MessageBox.Show("Grid Values can't be empty","Error",MessageBoxButtons.OK,MessageBoxIcon.Error);
                        retval = false;
                        break;
                    }
                }
                if (!retval)
                {
                    break;
                }
            }
            return retval;
        }

        private void label1_Click_1(object sender, EventArgs e)
        {

        }
        private void grdvw_Properties_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {
            if (e.RowIndex >= 0)
            {
                currentrowindex = e.RowIndex;
                DataGridViewRow row = this.grdvw_Properties.Rows[currentrowindex];
                String text = row.Cells[1].Value.ToString();
                this.txtFromDialog = text;

                ShowDialogBox(text);

                row = this.grdvw_Properties.Rows[currentrowindex];
                row.Cells[1].Value = txtFromDialog;
            }
        }
        private void grdvw_Properties_CellFormatting(object sender, DataGridViewCellFormattingEventArgs e)
        {

        }

        public void ShowDialogBox(String text)
        {
            Editmodal testDialog = new Editmodal();
            testDialog.SetTextBoxValue(text);
            // Show testDialog as a modal dialog and determine if DialogResult = OK.
            if (testDialog.ShowDialog(this) == DialogResult.OK)
            {
                // Read the contents of testDialog's TextBox.
                this.txtFromDialog = testDialog.GetTextBoxValue();
            }
            else
            {
                //do nothing
            }
            testDialog.Dispose();
        }

        private void grdvw_methods_cmbTest_SelectedIndexChanged(object sender, EventArgs e)
        {
            if(this.currentcombovalue.ToUpper() != this.cmbTest.SelectedItem.ToString().ToUpper())
            {
                this.currentcombovalue = this.cmbTest.SelectedItem.ToString();
                LoadGrdvwMethods();
            }
        }

        private void btn_Save_Click(object sender, EventArgs e)
        {
            CreateUserFolder();
            if (SaveProperties())
            {
                SaveXml();
                SaveExecutable();
                MessageBox.Show("Properties, XML, Batch File Saved Successfully in " + Path.GetDirectoryName(this.PropetiesFileToWrite));
                this.Close();
            }
        }

        private void CreateUserFolder()
        {
            if (!Directory.Exists(Path.GetDirectoryName(this.PropetiesFileToWrite)))
            {
                Directory.CreateDirectory(Path.GetDirectoryName(this.PropetiesFileToWrite));
            }
            


        }

        private Boolean SaveProperties()
        {
            String allGridText = "";
            Boolean retvalue = false;
            try
            {
                for (int i = 0; i < this.grdvw_Properties.Rows.Count; i++)
                {
                    String propkey = this.grdvw_Properties.Rows[i].Cells[0].Value.ToString();
                    String propvalue = this.grdvw_Properties.Rows[i].Cells[1].Value.ToString();
                    allGridText = allGridText + propkey + "=" + propvalue + Environment.NewLine;
                }
                allGridText = allGridText + "browsertype" + "=" + this.cmb_Browser.SelectedItem.ToString() + Environment.NewLine;


                if (allGridText.IndexOf("hhrr" + "=" + this.hhrr) < 0)
                {
                    allGridText = allGridText + "hhrr" + "=" + this.hhrr + Environment.NewLine;
                }
                if (allGridText.IndexOf("satamodeName" + "=" + this.datamodename) < 0)
                {
                    allGridText = allGridText + "datamodeName" + "=" + this.datamodename + Environment.NewLine;
                }
                if (allGridText.IndexOf("datamodeNo" + "=" + this.datamodeno) < 0)
                {
                    allGridText = allGridText + "datamodeNo" + "=" + this.datamodeno + Environment.NewLine;
                }

                if (File.Exists(this.PropetiesFileToWrite))
                {
                    File.Delete(this.PropetiesFileToWrite);
                }
                if (this.ValidateEmptyCellInDataGridView(this.grdvw_Properties)) {
                    File.AppendAllText(this.PropetiesFileToWrite, allGridText);
                    retvalue = true;
                }
                else
                {
                    retvalue = false;
                }             
            }
            catch (Exception e)
            {
                MessageBox.Show(e.Message,"Error-GridSave",MessageBoxButtons.OK,MessageBoxIcon.Error);
                retvalue = false;
            }
            return retvalue;

        }

        private void SaveXml()
        {
            SetTestSuitAndTestnameInXML();
            
            SaveMethodsInXml();
            AddParameterInXML();

            if (File.Exists(this.TestXMLFileToWrite))
            {
                File.Delete(this.TestXMLFileToWrite);
            }
            testNgXmlParser.SaveXmlToFile(this.TestXMLFileToWrite);
        }

        private void SaveExecutable()
        {
            string text = File.ReadAllText(this.ExecutableFileForReading);
            text = text.Replace(System.Configuration.ConfigurationManager.AppSettings["ModelXml"], Path.GetFileName(this.TestXMLFileToWrite));

            if (File.Exists(this.ExecutableFileToWrite))
            {
                File.Delete(this.ExecutableFileToWrite);
            }

            File.WriteAllText(this.ExecutableFileToWrite, text);
        }


        private void SetTestSuitAndTestnameInXML()
        {
            testNgXmlParser.SetSuiteName(browser + "_" + userfilenameformat + "_" + "suite");
            testNgXmlParser.SetTestName(browser + "_" + userfilenameformat + "_" + "test", browser + "_" + userfilenameformat + "_" + "suite");
        }
        private void AddParameterInXML()
        {
            testNgXmlParser.AddParameter(System.Configuration.ConfigurationManager.AppSettings["userconfigfile"], browser + "_" + userfilenameformat + ".properties", browser + "_" + userfilenameformat + "_" + "test");
        }

        private void SaveMethodsInXml()
        {
            String methodstate = "";
            String methodname = "";
            String classname = this.cmbTest.SelectedItem.ToString();

            for (int i = 0; i < this.grdvw_methods.Rows.Count; i++)
            {
                if ((this.grdvw_methods.Rows[i].Cells[0].Value) is DBNull || (Convert.ToBoolean(this.grdvw_methods.Rows[i].Cells[0].Value) == false))
                {
                    methodstate = "exclude";

                }
                else
                {
                    methodstate = "include";
                }

                methodname = grdvw_methods.Rows[i].Cells[1].Value.ToString();

                testNgXmlParser.SetMethodStateByClass(classname, methodname, methodstate);
            }
        }

        public String PropetiesFileForReading
        {
            get
            {
                if (Directory.Exists(Path.GetDirectoryName(userConfigFile)))
                {
                    if (File.Exists(userConfigFile))
                    {
                        return this.userConfigFile;
                    }
                    else
                    {
                        return this.modelConfigFile;
                    }
                }
                else
                {
                    return this.modelConfigFile;
                }
            }
        }
        public String TestXMLFileForReading
        {
            get
            {
                if (Directory.Exists(Path.GetDirectoryName(userTestXMLFile)))
                {
                    if (File.Exists(userTestXMLFile))
                    {
                        return this.userTestXMLFile;
                    }
                    else
                    {
                        return this.modelTestXMLFile;
                    }
                }
                else
                {
                    return this.modelTestXMLFile;
                }
            }
        }
        public String ExecutableFileForReading
        {
            get
            {
                if (Directory.Exists(Path.GetDirectoryName(userExecutableFile)))
                {
                    if (File.Exists(userExecutableFile))
                    {
                        return this.userExecutableFile;
                    }
                    else
                    {
                        return this.modelExecutableFile;
                    }
                }
                else
                {
                    return this.modelExecutableFile;
                }
            }
        }
        public String PropetiesFileToWrite
        {
            get
            {
                return this.userConfigFile;
            }
        }
        public String TestXMLFileToWrite
        {
            get
            {
                return this.userTestXMLFile;
            }
        }
        public String ExecutableFileToWrite
        {
            get
            {
                return this.userExecutableFile;
            }
        }

        private void btn_cancel_Click(object sender, EventArgs e)
        {
            this.Close();
        }
    }
}
