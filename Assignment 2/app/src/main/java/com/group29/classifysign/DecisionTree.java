package com.group29.classifysign;

class DecisionTree {

    public static boolean result = false;
    private static DecisionTree tree = null;

    private DecisionTree(){
        // constructor declared private just to defeat the normal instantiation process from outside
    }

    public static DecisionTree getInstance(){

        if(tree == null){
            tree = new DecisionTree();
        }

        return tree;
    }

    public static boolean rootTree(double[] feature){
        if(feature[21] < 161.571){
            //return node2(feature);
        } else if( feature[21] >= 161.571){
            //return node3(feature);
        } else {
           // return false;
        }
        return true;
    }

    /*

    private static boolean node2(double[] feature){
        if(feature[15] < 173.2){
            return false;
        } else if( feature[15] >= 173.2){
            return node5(feature);
        } else {
            return true;
        }
    }

    private static boolean node3(double[] feature){
        if(feature[19] < 284.645){
            return node6(feature);
        } else if( feature[19] >= 284.645){
            return node7(feature);
        } else {
            return false;
        }
    }


    private static boolean node5(double[] feature){
        if(feature[17] < 207.158){
            return node8(feature);
        } else if( feature[17] >= 207.158){
            return node9(feature);
        } else {
            return true;
        }
    }

    private static boolean node6(double[] feature){
        if(feature[9] < 175.874){
            return node10(feature);
        } else if( feature[9] >= 175.874){
            return node11(feature);
        } else {
            return false;
        }
    }
    private static boolean node7(double[] feature){
        if(feature[12] < 74.5566){
            return node12(feature);
        } else if( feature[12] >= 74.5566){
            return node13(feature);
        } else {
            return true;
        }
    }
    private static boolean node8(double[] feature){
        if(feature[19] < 210.24){
            return false;
        } else if( feature[19] >= 210.24){
            return node15(feature);
        } else {
            return true;
        }
    }
    private static boolean node9(double[] feature){
        if(feature[3] < 94.8946){
            return node8(feature);
        } else if( feature[3] >= 94.8946){
            return node9(feature);
        } else {
            return true;
        }
    }
    private static boolean node10(double[] feature){
        if(feature[8] < 109.797){
            return node18(feature);
        } else if( feature[8] >= 109.797){
            return node19(feature);
        } else {
            return false;
        }
    }
    private static boolean node11(double[] feature){
        if(feature[15] < 291.275){
            return node20(feature);
        } else if( feature[15] >= 291.275){
            return node21(feature);
        } else {
            return true;
        }
    }
    private static boolean node12(double[] feature){
        if(feature[15] < 248.35){
            return node22(feature);
        } else if( feature[15] >= 248.35){
            return node23(feature);
        } else {
            return false;
        }
    }

    private static boolean node13(double[] feature){
        if(feature[11] < 820.641){
            return node24(feature);
        } else if( feature[11] >= 820.641){
            return node25(feature);
        } else {
            return true;
        }
    }*/






}
