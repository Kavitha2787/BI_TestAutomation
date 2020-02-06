using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace UserConfiguration
{
    public partial class Editmodal : Form
    {


        public Editmodal()
        {
            InitializeComponent();
        }

        private void Editmodal_Load(object sender, EventArgs e)
        {

        }

        private void btn_ok_Click(object sender, EventArgs e)
        {
            textboxvalue = this.richTextBox1.Text;
            this.Close();
        }

        private void btn_cancel_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        public void SetTextBoxValue(String value)
        {
            this.richTextBox1.Text = value;
        }
        public String GetTextBoxValue()
        {
            return this.richTextBox1.Text;
        }
    }
}
