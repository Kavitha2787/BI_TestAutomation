using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml;
using System.Web.UI.WebControls;
using System.Xml.Linq;

class TestNgXmlParser
{
    XmlDocument xmldoc;
    XmlElement newnode;
    public TestNgXmlParser(String pathofxml)
    {
        xmldoc = new XmlDocument();
        xmldoc.Load(pathofxml);

    }
    public String[] GetAllClassNames()
    {
        List<String> retval = new List<String>();
        try
        {
            foreach (XmlNode node in xmldoc.DocumentElement.ChildNodes)
            {
                // first node is the url ... have to go to nexted loc node
                foreach (XmlNode locNode in node)

                {
                    //String d = locNode.Name.ToString();
                    foreach (XmlNode childNode in locNode)
                    {
                        // thereare a couple child nodes here so only take data from node named loc
                        if (childNode.Name == "class")
                        {
                            //get the content of the loc node
                            retval.Add(childNode.Attributes["name"].Value);

                        }

                    }
                }
            }

        }

        catch (Exception ex)
        {
            Console.WriteLine(ex);
        }
        return retval.ToArray();
    }

    public String[] GetAllMethodsByClassName(String classname)
    {
        List<String> retval = new List<String>();
        XmlNodeList nodeList = xmldoc.SelectNodes("//classes/class[@name='" + classname + "']");
        //Loop through the selected Nodes.
        if (nodeList[0].NextSibling != null)
        {

            if (nodeList[0].NextSibling.Name == "methods")

            {
                XmlNode listofmethods = nodeList[0].NextSibling;
                foreach (XmlNode locNode in listofmethods)

                {
                    if (locNode.Name == "include" || locNode.Name == "exclude")
                    {
                        string methodname = locNode.Attributes["name"].Value;
                        string inexcludetag = locNode.Name;
                        retval.Add(methodname);
                    }
                }
            }

        }
        return retval.ToArray();
    }

    public String GetMethodStateByClass(String classname, String Methodname)
    {
        String retval = null;
        XmlNodeList nodeList = xmldoc.SelectNodes("//classes/class[@name='" + classname + "']");
        if (nodeList[0].NextSibling.Name == "methods")

        {
            XmlNode listofmethods = nodeList[0].NextSibling;
            foreach (XmlNode locNode in listofmethods)

            {
                if (locNode.Attributes["name"].Value == Methodname)
                {


                    if (locNode.Name == "include" || locNode.Name == "exclude")
                    {


                        retval = locNode.Name;

                    }
                }

            }
        }
        return retval;
    }
    public void SaveXmlToFile(String filepath)
    {
        xmldoc.Save(filepath);
    }
    public void AddParameter(String parametername, String parametervalue, String testname)
    {

        //XmlNodeList nodeList = xmldoc.SelectNodes("\\test");
        newnode = xmldoc.CreateElement("parameter");
        newnode.SetAttribute("name", parametername);
        newnode.SetAttribute("value", parametervalue);
        foreach (XmlNode node in xmldoc.DocumentElement.ChildNodes)
        {
            if (node.Name == "test")
            {
                if (node.Attributes["name"].Value.ToUpper() == testname.ToUpper())
                {
                    foreach (XmlNode paranode in node)
                    {
                        if (paranode.Name == "parameter")
                        {
                            if (paranode.Attributes["name"].Value.ToUpper() == parametername.ToUpper() && paranode.Attributes["value"].Value.ToUpper() == parametervalue.ToUpper())
                            {

                            }
                            else
                            {
                                node.RemoveChild(node.FirstChild);
                                node.AppendChild(newnode);
                                break;
                            }
                        }
                    }

                }

            }
        }
        //

    }
    //public void SetParameterValue(String value)
    //{
    //    newnode.SetAttribute("value", value);
    //}
    public void SetSuiteName(String suitename)
    {
        XmlNode nodeList = xmldoc.SelectSingleNode("suite");
        nodeList.Attributes["name"].Value = suitename;
    }
    public void SetTestName(String testname, String suitename)
    {
        XmlNodeList nodeList = xmldoc.SelectNodes("//suite[@name='" + suitename + "']/test");
        nodeList[0].Attributes["name"].Value = testname;
    }

    public void SetMethodStateByClass(String classname, String methodname, String methodstate)
    {

        XmlNodeList nodeList = xmldoc.SelectNodes("//classes/class[@name='" + classname + "']");
        if (nodeList[0].NextSibling.Name == "methods")
        {
            XmlNode listofmethods = nodeList[0].NextSibling;
            foreach (XmlNode locNode in listofmethods)
            {
                if (locNode.Attributes["name"].Value == methodname)
                {
                    if (locNode.Name == "include" || locNode.Name == "exclude")
                    {
                        String newstate = "";
                        XmlElement newnode;

                        if (methodstate == "include")
                        {
                            newstate = methodstate;
                            newnode = xmldoc.CreateElement(newstate);
                            newnode.SetAttribute("name", methodname);

                            listofmethods.RemoveChild(locNode);
                            listofmethods.AppendChild(newnode);
                            break;
                        }

                        if (methodstate == "exclude")
                        {
                            newstate = methodstate;
                            newnode = xmldoc.CreateElement(newstate);
                            newnode.SetAttribute("name", methodname);

                            listofmethods.RemoveChild(locNode);
                            listofmethods.AppendChild(newnode);
                            break;

                        }

                    }
                }
            }


        }

    }
}

