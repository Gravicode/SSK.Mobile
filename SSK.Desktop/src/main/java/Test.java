import org.ini4j.Ini;
import org.ini4j.IniPreferences;
import pkdss.corelibrary.FertilizerCalculator;
import pkdss.corelibrary.ModelRunner;
import pkdss.corelibrary.model.InferenceResult;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

//import static sun.misc.Version.print;
public class Test {
    public static void main(String[] args) {
        String filename = "C:\\jobs\\balittanah-pkdss\\SSK.Desktop\\src\\main\\java\\data\\config.ini";
        Ini ini = null;
        try {
            ini = new Ini(new File(filename));
            java.util.prefs.Preferences prefs = new IniPreferences(ini);
            System.out.println("grumpy/homePage: " + prefs.node("grumpy").get("homePage", null));

            String DataRekomendasi = ini.get("Config","DataRekomendasi");
            String WorkingDirectory = ini.get("Config","WorkingDirectory");
            String ModelScript = ini.get("Config","ModelScript");
            String SensorData = ini.get("Config","SensorData");
            String AnacondaFolder = ini.get("Config","AnacondaFolder");
            ModelRunner ml = new ModelRunner(WorkingDirectory, ModelScript, SensorData, AnacondaFolder);
            InferenceResult hasil = ml.InferenceModel(false, true);
            if (hasil.getIsSucceed())
            {
                try
                {

                    System.out.println("start recommendation procecss");
                    FertilizerCalculator calc = new FertilizerCalculator(DataRekomendasi);
                    String TxtUrea = String.valueOf(calc.GetFertilizerDoze(hasil.getModel().getCN(), "Padi", "Urea"));
                    String TxtSP36 = String.valueOf(calc.GetFertilizerDoze(hasil.getModel().getHCl25P2O5(), "Padi", "SP36"));
                    String TxtKCL = String.valueOf(calc.GetFertilizerDoze(hasil.getModel().getHCl25K2O(), "Padi", "KCL"));
                    System.out.println(String.format("Rekomendasi KCL : %1$s, SP36 : %2$s, Urea : %3$s", TxtKCL, TxtSP36, TxtUrea));

                }
                catch (RuntimeException ex)
                {
                    System.out.println(ex);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void print(PrintStream out) {
        out.println("Hello, World!");
    }
}
