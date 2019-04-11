import java.util.Map;

//public class PlaceFactory {

  //  public PlaceFactory(){

    //}






    //public static Place newInstance(Map<String, Object> param) {
      //  if (param.containsKey("name")) {
        //    Place place = new Place(param.get("name").toString());

          //  return place;
        //} else {
          //  return null;
        //}
//    }


//}


/**import java.util.Map;

public class PlaceFactory {

    public PlaceFactory(){

    }




   /** public static Place newInstance(Map<String, Object> param) throws Exception {
        Place place = null;
        if (param.containsKey("name")) {
            String name = null;
            try {
                name = param.get("name").toString();
            } catch (NumberException e) {
                name = (String) param.get("name");
            }
            place = new Place(name);
        } else {
            throw new Exception();

        }
        return place;


    }

 */
public class PlaceFactory {

    private PlaceFactory() {
    }

    /**
     * @param things параметр для создания Character
     * @return объект типа Character
     * @throws Exception если параметров не достаточно
     */
    public static Place newInstance(Map<String, String> things) throws Exception {
        Place place = null;
        String x=null;
        String y = null;

        if (things.containsKey("name")) {

            String name = things.get("name");




        if (things.containsKey("x")) {

             x = things.get("x");

        }


        if (things.containsKey("y")){

            y = things.get("y");



        }
            place = new Place(name,x,y);

        }

        else
                {
            throw new Exception();
        }
        return place;

    }
}



