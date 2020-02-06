namespace UserConfiguration
{
    partial class Editmodal
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.richTextBox1 = new System.Windows.Forms.RichTextBox();
            this.btn_cancel = new System.Windows.Forms.Button();
            this.btn_ok = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // richTextBox1
            // 
            this.richTextBox1.Location = new System.Drawing.Point(12, 12);
            this.richTextBox1.Name = "richTextBox1";
            this.richTextBox1.Size = new System.Drawing.Size(660, 599);
            this.richTextBox1.TabIndex = 1;
            this.richTextBox1.Text = "";

            // 
            // btn_cancel
            // 
            this.btn_cancel.Location = new System.Drawing.Point(597, 617);
            this.btn_cancel.Name = "btn_cancel";
            this.btn_cancel.Size = new System.Drawing.Size(75, 23);
            this.btn_cancel.TabIndex = 3;
            this.btn_cancel.Text = "Cancel";
            this.btn_cancel.UseVisualStyleBackColor = true;
            this.btn_cancel.Click += new System.EventHandler(this.btn_cancel_Click);
            this.btn_cancel.DialogResult = System.Windows.Forms.DialogResult.Cancel;
            // 
            // btn_ok
            // 
            this.btn_ok.Location = new System.Drawing.Point(499, 617);
            this.btn_ok.Name = "btn_ok";
            this.btn_ok.Size = new System.Drawing.Size(75, 23);
            this.btn_ok.TabIndex = 2;
            this.btn_ok.Text = "Ok";
            this.btn_ok.UseVisualStyleBackColor = true;
            this.btn_ok.Click += new System.EventHandler(this.btn_ok_Click);
            this.btn_ok.DialogResult = System.Windows.Forms.DialogResult.OK;
            // 
            // Editmodal
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(684, 661);
            this.Controls.Add(this.btn_cancel);
            this.Controls.Add(this.btn_ok);
            this.Controls.Add(this.richTextBox1);
            this.MaximumSize = new System.Drawing.Size(700, 700);
            this.Name = "Editmodal";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterParent;
            this.Text = "Edit";
            this.Load += new System.EventHandler(this.Editmodal_Load);
            this.ResumeLayout(false);

        }

        #endregion


        private System.Windows.Forms.RichTextBox richTextBox1;
        private System.Windows.Forms.Button btn_cancel;
        private System.Windows.Forms.Button btn_ok;
        private string textboxvalue;
    }
}