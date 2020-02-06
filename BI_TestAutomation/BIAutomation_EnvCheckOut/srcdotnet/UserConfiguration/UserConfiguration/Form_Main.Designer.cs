namespace UserConfiguration
{
    partial class frm_UserConfiguration
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
            this.lbl_Browser = new System.Windows.Forms.Label();
            this.lbl_HHRR = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.cmb_Browser = new System.Windows.Forms.ComboBox();
            this.txt_HHRR = new System.Windows.Forms.TextBox();
            this.txt_DataModeName = new System.Windows.Forms.TextBox();
            this.txt_DataModeNo = new System.Windows.Forms.TextBox();
            this.btn_Load = new System.Windows.Forms.Button();
            this.tabctrl_propmethods = new System.Windows.Forms.TabControl();
            this.tab_Properties = new System.Windows.Forms.TabPage();
            this.grdvw_Properties = new System.Windows.Forms.DataGridView();
            this.grdvw_Properties_txtPropKey = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.grdvw_Properties_txtPropValue = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.grdvw_Properties_linkEditPropValue = new System.Windows.Forms.DataGridViewLinkColumn();
            this.tab_Methods = new System.Windows.Forms.TabPage();
            this.grdvw_methods_lblTest = new System.Windows.Forms.Label();
            this.cmbTest = new System.Windows.Forms.ComboBox();
            this.grdvw_methods = new System.Windows.Forms.DataGridView();
            this.grdvw_methods_chkboxinclude = new System.Windows.Forms.DataGridViewCheckBoxColumn();
            this.grdvw_methods_txtMethods = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.btn_cancel = new System.Windows.Forms.Button();
            this.btn_Save = new System.Windows.Forms.Button();
            this.tabctrl_propmethods.SuspendLayout();
            this.tab_Properties.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.grdvw_Properties)).BeginInit();
            this.tab_Methods.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.grdvw_methods)).BeginInit();
            this.SuspendLayout();
            // 
            // lbl_Browser
            // 
            this.lbl_Browser.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.lbl_Browser.AutoSize = true;
            this.lbl_Browser.Font = new System.Drawing.Font("Arial", 10F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lbl_Browser.Location = new System.Drawing.Point(55, 9);
            this.lbl_Browser.Name = "lbl_Browser";
            this.lbl_Browser.Size = new System.Drawing.Size(71, 16);
            this.lbl_Browser.TabIndex = 0;
            this.lbl_Browser.Text = "Browser:";
            this.lbl_Browser.Click += new System.EventHandler(this.label1_Click);
            // 
            // lbl_HHRR
            // 
            this.lbl_HHRR.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.lbl_HHRR.AutoSize = true;
            this.lbl_HHRR.Font = new System.Drawing.Font("Arial", 10F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lbl_HHRR.Location = new System.Drawing.Point(272, 9);
            this.lbl_HHRR.Name = "lbl_HHRR";
            this.lbl_HHRR.Size = new System.Drawing.Size(52, 16);
            this.lbl_HHRR.TabIndex = 1;
            this.lbl_HHRR.Text = "HHRR:";
            // 
            // label3
            // 
            this.label3.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.label3.AutoSize = true;
            this.label3.Font = new System.Drawing.Font("Arial", 10F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label3.Location = new System.Drawing.Point(405, 9);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(122, 16);
            this.label3.TabIndex = 2;
            this.label3.Text = "DataModeName:";
            // 
            // label4
            // 
            this.label4.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.label4.AutoSize = true;
            this.label4.Font = new System.Drawing.Font("Arial", 10F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label4.Location = new System.Drawing.Point(606, 9);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(102, 16);
            this.label4.TabIndex = 3;
            this.label4.Text = "DataModeNo:";
            this.label4.Click += new System.EventHandler(this.label4_Click);
            // 
            // cmb_Browser
            // 
            this.cmb_Browser.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.cmb_Browser.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cmb_Browser.FormattingEnabled = true;
            this.cmb_Browser.Location = new System.Drawing.Point(125, 6);
            this.cmb_Browser.Name = "cmb_Browser";
            this.cmb_Browser.Size = new System.Drawing.Size(121, 24);
            this.cmb_Browser.TabIndex = 4;
            // 
            // txt_HHRR
            // 
            this.txt_HHRR.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.txt_HHRR.Location = new System.Drawing.Point(322, 6);
            this.txt_HHRR.Name = "txt_HHRR";
            this.txt_HHRR.Size = new System.Drawing.Size(53, 23);
            this.txt_HHRR.TabIndex = 5;
            // 
            // txt_DataModeName
            // 
            this.txt_DataModeName.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.txt_DataModeName.Location = new System.Drawing.Point(525, 6);
            this.txt_DataModeName.Name = "txt_DataModeName";
            this.txt_DataModeName.Size = new System.Drawing.Size(53, 23);
            this.txt_DataModeName.TabIndex = 6;
            // 
            // txt_DataModeNo
            // 
            this.txt_DataModeNo.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.txt_DataModeNo.Location = new System.Drawing.Point(706, 6);
            this.txt_DataModeNo.Name = "txt_DataModeNo";
            this.txt_DataModeNo.Size = new System.Drawing.Size(53, 23);
            this.txt_DataModeNo.TabIndex = 7;
            // 
            // btn_Load
            // 
            this.btn_Load.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.btn_Load.Location = new System.Drawing.Point(798, 6);
            this.btn_Load.Name = "btn_Load";
            this.btn_Load.Size = new System.Drawing.Size(75, 24);
            this.btn_Load.TabIndex = 8;
            this.btn_Load.Text = "L&oad";
            this.btn_Load.UseVisualStyleBackColor = true;
            this.btn_Load.Click += new System.EventHandler(this.btn_Load_Click);
            // 
            // tabctrl_propmethods
            // 
            this.tabctrl_propmethods.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.tabctrl_propmethods.Controls.Add(this.tab_Properties);
            this.tabctrl_propmethods.Controls.Add(this.tab_Methods);
            this.tabctrl_propmethods.Location = new System.Drawing.Point(10, 74);
            this.tabctrl_propmethods.Margin = new System.Windows.Forms.Padding(5, 3, 5, 3);
            this.tabctrl_propmethods.MinimumSize = new System.Drawing.Size(890, 513);
            this.tabctrl_propmethods.Multiline = true;
            this.tabctrl_propmethods.Name = "tabctrl_propmethods";
            this.tabctrl_propmethods.SelectedIndex = 0;
            this.tabctrl_propmethods.Size = new System.Drawing.Size(890, 513);
            this.tabctrl_propmethods.TabIndex = 9;
            // 
            // tab_Properties
            // 
            this.tab_Properties.Controls.Add(this.grdvw_Properties);
            this.tab_Properties.Location = new System.Drawing.Point(4, 25);
            this.tab_Properties.Name = "tab_Properties";
            this.tab_Properties.Padding = new System.Windows.Forms.Padding(3);
            this.tab_Properties.Size = new System.Drawing.Size(882, 484);
            this.tab_Properties.TabIndex = 0;
            this.tab_Properties.Text = "Configure Properties";
            this.tab_Properties.UseVisualStyleBackColor = true;
            // 
            // grdvw_Properties
            // 
            this.grdvw_Properties.AllowUserToAddRows = false;
            this.grdvw_Properties.AllowUserToDeleteRows = false;
            this.grdvw_Properties.AllowUserToResizeColumns = false;
            this.grdvw_Properties.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.grdvw_Properties.AutoSizeColumnsMode = System.Windows.Forms.DataGridViewAutoSizeColumnsMode.Fill;
            this.grdvw_Properties.AutoSizeRowsMode = System.Windows.Forms.DataGridViewAutoSizeRowsMode.AllCells;
            this.grdvw_Properties.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.grdvw_Properties.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.grdvw_Properties_txtPropKey,
            this.grdvw_Properties_txtPropValue,
            this.grdvw_Properties_linkEditPropValue});
            this.grdvw_Properties.Location = new System.Drawing.Point(6, 6);
            this.grdvw_Properties.Name = "grdvw_Properties";
            this.grdvw_Properties.RowHeadersVisible = false;
            this.grdvw_Properties.Size = new System.Drawing.Size(870, 472);
            this.grdvw_Properties.TabIndex = 0;
            this.grdvw_Properties.CellContentClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.grdvw_Properties_CellContentClick);
            this.grdvw_Properties.CellFormatting += new System.Windows.Forms.DataGridViewCellFormattingEventHandler(this.grdvw_Properties_CellFormatting);
            // 
            // grdvw_Properties_txtPropKey
            // 
            this.grdvw_Properties_txtPropKey.FillWeight = 76.14214F;
            this.grdvw_Properties_txtPropKey.HeaderText = "Properties Name";
            this.grdvw_Properties_txtPropKey.MinimumWidth = 200;
            this.grdvw_Properties_txtPropKey.Name = "grdvw_Properties_txtPropKey";
            this.grdvw_Properties_txtPropKey.ReadOnly = true;
            this.grdvw_Properties_txtPropKey.Resizable = System.Windows.Forms.DataGridViewTriState.False;
            // 
            // grdvw_Properties_txtPropValue
            // 
            this.grdvw_Properties_txtPropValue.FillWeight = 111.9289F;
            this.grdvw_Properties_txtPropValue.HeaderText = "Properties Value";
            this.grdvw_Properties_txtPropValue.MinimumWidth = 600;
            this.grdvw_Properties_txtPropValue.Name = "grdvw_Properties_txtPropValue";
            this.grdvw_Properties_txtPropValue.ReadOnly = true;
            this.grdvw_Properties_txtPropValue.Resizable = System.Windows.Forms.DataGridViewTriState.False;
            // 
            // grdvw_Properties_linkEditPropValue
            // 
            this.grdvw_Properties_linkEditPropValue.FillWeight = 111.9289F;
            this.grdvw_Properties_linkEditPropValue.HeaderText = "Edit";
            this.grdvw_Properties_linkEditPropValue.MinimumWidth = 60;
            this.grdvw_Properties_linkEditPropValue.Name = "grdvw_Properties_linkEditPropValue";
            this.grdvw_Properties_linkEditPropValue.ReadOnly = true;
            this.grdvw_Properties_linkEditPropValue.Resizable = System.Windows.Forms.DataGridViewTriState.False;
            this.grdvw_Properties_linkEditPropValue.Text = "Edit";
            this.grdvw_Properties_linkEditPropValue.UseColumnTextForLinkValue = true;
            // 
            // tab_Methods
            // 
            this.tab_Methods.Controls.Add(this.grdvw_methods);
            this.tab_Methods.Location = new System.Drawing.Point(4, 25);
            this.tab_Methods.Name = "tab_Methods";
            this.tab_Methods.Padding = new System.Windows.Forms.Padding(3);
            this.tab_Methods.Size = new System.Drawing.Size(882, 484);
            this.tab_Methods.TabIndex = 1;
            this.tab_Methods.Text = "Select Methods";
            this.tab_Methods.UseVisualStyleBackColor = true;
            // 
            // grdvw_methods_lblTest
            // 
            this.grdvw_methods_lblTest.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.grdvw_methods_lblTest.AutoSize = true;
            this.grdvw_methods_lblTest.Font = new System.Drawing.Font("Arial", 10F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.grdvw_methods_lblTest.Location = new System.Drawing.Point(315, 47);
            this.grdvw_methods_lblTest.Name = "grdvw_methods_lblTest";
            this.grdvw_methods_lblTest.Size = new System.Drawing.Size(41, 16);
            this.grdvw_methods_lblTest.TabIndex = 6;
            this.grdvw_methods_lblTest.Text = "Test:";
            this.grdvw_methods_lblTest.Click += new System.EventHandler(this.label1_Click_1);
            // 
            // cmbTest
            // 
            this.cmbTest.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.cmbTest.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cmbTest.FormattingEnabled = true;
            this.cmbTest.Location = new System.Drawing.Point(362, 44);
            this.cmbTest.Name = "cmbTest";
            this.cmbTest.Size = new System.Drawing.Size(225, 24);
            this.cmbTest.TabIndex = 5;
            this.cmbTest.SelectedIndexChanged += new System.EventHandler(this.grdvw_methods_cmbTest_SelectedIndexChanged);
            // 
            // grdvw_methods
            // 
            this.grdvw_methods.AllowUserToAddRows = false;
            this.grdvw_methods.AllowUserToDeleteRows = false;
            this.grdvw_methods.AllowUserToResizeColumns = false;
            this.grdvw_methods.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.grdvw_methods.AutoSizeColumnsMode = System.Windows.Forms.DataGridViewAutoSizeColumnsMode.Fill;
            this.grdvw_methods.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.grdvw_methods.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.grdvw_methods_chkboxinclude,
            this.grdvw_methods_txtMethods});
            this.grdvw_methods.Location = new System.Drawing.Point(7, 6);
            this.grdvw_methods.Name = "grdvw_methods";
            this.grdvw_methods.RowHeadersVisible = false;
            this.grdvw_methods.Size = new System.Drawing.Size(870, 472);
            this.grdvw_methods.TabIndex = 1;
            // 
            // grdvw_methods_chkboxinclude
            // 
            this.grdvw_methods_chkboxinclude.FillWeight = 30.45685F;
            this.grdvw_methods_chkboxinclude.HeaderText = "Include/Exclude";
            this.grdvw_methods_chkboxinclude.MinimumWidth = 30;
            this.grdvw_methods_chkboxinclude.Name = "grdvw_methods_chkboxinclude";
            // 
            // grdvw_methods_txtMethods
            // 
            this.grdvw_methods_txtMethods.FillWeight = 169.5432F;
            this.grdvw_methods_txtMethods.HeaderText = "Method Name";
            this.grdvw_methods_txtMethods.Name = "grdvw_methods_txtMethods";
            this.grdvw_methods_txtMethods.ReadOnly = true;
            // 
            // btn_cancel
            // 
            this.btn_cancel.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.btn_cancel.Location = new System.Drawing.Point(817, 594);
            this.btn_cancel.Name = "btn_cancel";
            this.btn_cancel.Size = new System.Drawing.Size(75, 24);
            this.btn_cancel.TabIndex = 10;
            this.btn_cancel.Text = "Ca&ncel";
            this.btn_cancel.Click += new System.EventHandler(this.btn_cancel_Click);
            // 
            // btn_Save
            // 
            this.btn_Save.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.btn_Save.Location = new System.Drawing.Point(736, 594);
            this.btn_Save.Name = "btn_Save";
            this.btn_Save.Size = new System.Drawing.Size(75, 24);
            this.btn_Save.TabIndex = 11;
            this.btn_Save.Text = "&Save";
            this.btn_Save.UseVisualStyleBackColor = true;
            this.btn_Save.Click += new System.EventHandler(this.btn_Save_Click);
            // 
            // frm_UserConfiguration
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(914, 690);
            this.Controls.Add(this.grdvw_methods_lblTest);
            this.Controls.Add(this.btn_Save);
            this.Controls.Add(this.cmbTest);
            this.Controls.Add(this.btn_cancel);
            this.Controls.Add(this.tabctrl_propmethods);
            this.Controls.Add(this.btn_Load);
            this.Controls.Add(this.txt_DataModeNo);
            this.Controls.Add(this.txt_DataModeName);
            this.Controls.Add(this.txt_HHRR);
            this.Controls.Add(this.cmb_Browser);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.lbl_HHRR);
            this.Controls.Add(this.lbl_Browser);
            this.Font = new System.Drawing.Font("Arial", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.Margin = new System.Windows.Forms.Padding(4);
            this.MinimumSize = new System.Drawing.Size(930, 726);
            this.Name = "frm_UserConfiguration";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterParent;
            this.Text = "UserConfiguration";
            this.Load += new System.EventHandler(this.frm_UserConfiguration_Load);
            this.tabctrl_propmethods.ResumeLayout(false);
            this.tab_Properties.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.grdvw_Properties)).EndInit();
            this.tab_Methods.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.grdvw_methods)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label lbl_Browser;
        private System.Windows.Forms.Label lbl_HHRR;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.ComboBox cmb_Browser;
        private System.Windows.Forms.TextBox txt_HHRR;
        private System.Windows.Forms.TextBox txt_DataModeName;
        private System.Windows.Forms.TextBox txt_DataModeNo;
        private System.Windows.Forms.Button btn_Load;
        private System.Windows.Forms.TabControl tabctrl_propmethods;
        private System.Windows.Forms.TabPage tab_Properties;
        private System.Windows.Forms.TabPage tab_Methods;
        private System.Windows.Forms.DataGridView grdvw_Properties;
        private System.Windows.Forms.DataGridView grdvw_methods;
        private System.Windows.Forms.DataGridViewCheckBoxColumn grdvw_methods_chkboxinclude;
        private System.Windows.Forms.DataGridViewTextBoxColumn grdvw_methods_txtMethods;
        private System.Windows.Forms.DataGridViewTextBoxColumn grdvw_Properties_txtPropKey;
        private System.Windows.Forms.DataGridViewTextBoxColumn grdvw_Properties_txtPropValue;
        private System.Windows.Forms.DataGridViewLinkColumn grdvw_Properties_linkEditPropValue;
        private System.Windows.Forms.Label grdvw_methods_lblTest;
        private System.Windows.Forms.ComboBox cmbTest;
        private System.Windows.Forms.Button btn_cancel;
        private System.Windows.Forms.Button btn_Save;
    }
}

