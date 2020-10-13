import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @Auther: Jake.Chen
 * @Date: 9/4/2020 10:50
 * @Description:
 */
public class TestMain {
    public static void main(String[] args) {

        TestMain testMain = new TestMain();
        testMain.testPathJoin();
    }

    public void testGetlogPath() {
        String site_name = "COLO-WWW2-itrade";
        String log_path = "result/2020/09/25/COLO-WWW2-itrade-All-no-log-All-no-log-All-no-log";
        System.out.println(getPathSiteName_v0(log_path));
        System.out.println(getPathSiteName_v1(log_path));

    }

    private String getPathSiteName_v1(String path) {
        String pathSiteName = "";

        if ("".equals(path) || path == null) {
            return null;
        }

        String[] pathStr = path.split("/");
        int filenameIndex = pathStr.length - 1;
        String filename = pathStr[filenameIndex];

        String[] nameStrArr = filename.split("-All-");
        int nameSuffixIndex = nameStrArr.length-1;
        String filenameSuffix = "-All-" + nameStrArr[nameSuffixIndex];

        int subIndex = filename.length()- filenameSuffix.length();
        pathSiteName = filename.substring(0,subIndex);

        return pathSiteName;
    }
    private String getPathSiteName_v0(String path) {
        if ("".equals(path) || path == null) {
            return null;
        }
        String[] pathStr = path.split("/");
        int fileIndex = pathStr.length - 1;
        String fileName = pathStr[fileIndex];

        String[] fileNameStr = fileName.split("-");
        int siteNameIndex = 0;
        String siteName = fileNameStr[siteNameIndex];
        return siteName;
    }
    public void testPathJoin(){
        Path path = Paths.get("/home/rawlog/log_file/","/2020/10/12/ap1-all-10-12-2020.zip");
        System.out.println(path.normalize());
    }

}
