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
            return node2(feature);
        } else if( feature[21] >= 161.571){
            return node3(feature);
        } else {
            return false;
        }
    }



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
            return node16(feature);
        } else if( feature[3] >= 94.8946){
            return node17(feature);
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
            return false;
        } else if( feature[15] >= 291.275){
            return true;
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
            return false;
        } else {
            return true;
        }
    }

    // start changing feature

    private static boolean node15(double[] feature){
        if(feature[2] < 143.334){
            return node26(feature);
        } else if( feature[2] >= 143.334){
            return node27(feature);
        } else {
            return true;
        }
    }

    private static boolean node16(double[] feature){
        if(feature[21] < 127.844){
            return node28(feature);
        } else if( feature[21] >= 127.844){
            return node29(feature);
        } else {
            return true;
        }
    }

    private static boolean node17(double[] feature){
        if(feature[12] < 32.873){
            return false;
        } else if( feature[12] >= 32.873){
            return node31(feature);
        } else {
            return true;
        }
    }

    private static boolean node18(double[] feature){
        if(feature[19] < 151.595){
            return node32(feature);
        } else if( feature[19] >= 151.595){
            return node33(feature);
        } else {
            return false;
        }
    }

    private static boolean node19(double[] feature){
        if(feature[14] < 177.075){
            return node34(feature);
        } else if( feature[14] >= 177.075){
            return node35(feature);
        } else {
            return false;
        }
    }

    private static boolean node22(double[] feature){
        if(feature[0] < 121.89){
            return true;
        } else if( feature[0] >= 121.89){
            return false;
        } else {
            return true;
        }
    }

    private static boolean node23(double[] feature){
        if(feature[19] < 337.018){
            return node38(feature);
        } else if( feature[19] >= 337.018){
            return node39(feature);
        } else {
            return false;
        }
    }

    private static boolean node24(double[] feature){
        if(feature[16] < 19.9827){
            return node40(feature);
        } else if( feature[16] >= 19.9827){
            return node41(feature);
        } else {
            return true;
        }
    }

    private static boolean node26(double[] feature){
        if(feature[17] < 122.767){
            return false;
        } else if( feature[17] >= 122.767){
            return node43(feature);
        } else {
            return true;
        }
    }

    //


    private static boolean node27(double[] feature){
        if(feature[1] < 73.876){
            return false;
        } else if( feature[1] >= 73.876){
            return true;
        } else {
            return false;
        }
    }


    private static boolean node28(double[] feature){
        if(feature[4] < 120.164){
            return node46(feature);
        } else if( feature[4] >= 120.164){
            return node47(feature);
        } else {
            return true;
        }
    }

    private static boolean node29(double[] feature){
        if(feature[16] < 20.4221){
            return node48(feature);
        } else if( feature[16] >= 20.4221){
            return node49(feature);
        } else {
            return false;
        }
    }

    private static boolean node31(double[] feature){
        if(feature[15] < 227.858){
            return node50(feature);
        } else if( feature[15] >= 227.858){
            return node51(feature);
        } else {
            return true;
        }
    }

    private static boolean node32(double[] feature){
        if(feature[1] < 115.03){
            return false;
        } else if( feature[1] >= 115.03){
            return true;
        } else {
            return true;
        }
    }
    private static boolean node33(double[] feature){
        if(feature[6] < 120.935){
            return node54(feature);
        } else if( feature[6] >= 120.935){
            return node55(feature);
        } else {
            return false;
        }
    }

    private static boolean node34(double[] feature){
        if(feature[5] < 97.4728){
            return false;
        } else if( feature[5] >= 97.4728){
            return node57(feature);
        } else {
            return true;
        }
    }
    private static boolean node35(double[] feature){
        if(feature[1] < 71.2788){
            return true;
        } else if( feature[1] >= 71.2788){
            return node59(feature);
        } else {
            return false;
        }
    }
    private static boolean node38(double[] feature){
        if(feature[14] < 211.773){
            return node60(feature);
        } else if( feature[14] >= 211.773){
            return node61(feature);
        } else {
            return false;
        }
    }

    private static boolean node39(double[] feature){
        if(feature[12] < 57.3893){
            return node62(feature);
        } else if( feature[12] >= 57.3893){
            return node63(feature);
        } else {
            return false;
        }
    }

    private static boolean node40(double[] feature){
        if(feature[17] < 258.45){
            return node64(feature);
        } else if( feature[17] >= 258.45){
            return node65(feature);
        } else {
            return false;
        }
    }
    private static boolean node41(double[] feature){
        if(feature[12] < 77.0423){
            return node66(feature);
        } else if( feature[12] >= 77.0423){
            return node67(feature);
        } else {
            return true;
        }
    }
    private static boolean node43(double[] feature){
        if(feature[17] < 187.762){
            return node68(feature);
        } else if( feature[17] >= 187.762){
            return false;
        } else {
            return true;
        }
    }
    private static boolean node46(double[] feature){
        if(feature[17] < 264.748){
            return node70(feature);
        } else if( feature[17] >= 264.748){
            return node71(feature);
        } else {
            return true;
        }
    }

    private static boolean node47(double[] feature){
        if(feature[10] < 176.042){
            return false;
        } else if( feature[10] >= 176.042){
            return true;
        } else {
            return true;
        }
    }

    private static boolean node48(double[] feature){
        if(feature[18] < 108.829){
            return true;
        } else if( feature[18] >= 108.829){
            return node75(feature);
        } else {
            return false;
        }
    }
    private static boolean node49(double[] feature){
        if(feature[19] < 255.287){
            return node76(feature);
        } else if( feature[19] >= 255.287){
            return true;
        } else {
            return false;
        }
    }
    private static boolean node50(double[] feature){
        if(feature[19] < 238.265){
            return false;
        } else if( feature[19] >= 238.265){
            return true;
        } else {
            return true;
        }
    }
    private static boolean node51(double[] feature){
        if(feature[3] < 99.0194){
            return node80(feature);
        } else if( feature[3] >= 99.0194){
            return true;
        } else {
            return true;
        }
    }
    private static boolean node54(double[] feature){
        if(feature[0] < 114.668){
            return false;
        } else if( feature[0] >= 114.668){
            return true;
        } else {
            return false;
        }
    }

    private static boolean node55(double[] feature){
        if(feature[12] < 100.287){
            return node84(feature);
        } else if( feature[12] >= 100.287){
            return node85(feature);
        } else {
            return false;
        }
    }


    private static boolean node57(double[] feature){
        if(feature[15] < 208.075){
            return false;
        } else if( feature[15] >= 208.075){
            return true;
        } else {
            return true;
        }
    }


    private static boolean node59(double[] feature){
        if(feature[6] < 137.581){
            return true;
        } else if( feature[6] >= 137.581){
            return false;
        } else {
            return false;
        }
    }


    private static boolean node60(double[] feature){
        if(feature[10] < 177.683){
            return false;
        } else if( feature[10] >= 177.683){
            return node91(feature);
        } else {
            return false;
        }
    }

    private static boolean node61(double[] feature){
        if(feature[17] < 212.424){
            return true;
        } else if( feature[17] >= 212.424){
            return node93(feature);
        } else {
            return false;
        }
    }


    private static boolean node62(double[] feature){
        if(feature[8] < 89.4306){
            return node94(feature);
        } else if( feature[8] >= 89.4306){
            return node95(feature);
        } else {
            return false;
        }
    }


    private static boolean node63(double[] feature){
        if(feature[1] < 150.003){
            return node96(feature);
        } else if( feature[1] >= 150.003){
            return false;
        } else {
            return true;
        }
    }


    private static boolean node64(double[] feature){
        if(feature[6] < 147.968){
            return true;
        } else if( feature[6] >= 147.968){
            return node99(feature);
        } else {
            return false;
        }
    }


    private static boolean node65(double[] feature){
        if(feature[19] < 307.044){
            return false;
        } else if( feature[19] >= 307.044){
            return true;
        } else {
            return true;
        }
    }



    private static boolean node66(double[] feature){
        if(feature[2] < 131.142){
            return true;
        } else if( feature[2] >= 131.142){
            return false;
        } else {
            return true;
        }
    }



    private static boolean node67(double[] feature){
        if(feature[12] < 78.8038){
            return node104(feature);
        } else if( feature[12] >= 78.8038){
            return node105(feature);
        } else {
            return true;
        }
    }


    private static boolean node68(double[] feature){
        if(feature[20] < 58.8251){
            return node106(feature);
        } else if( feature[20] >= 58.8251){
            return node107(feature);
        } else {
            return true;
        }
    }


    private static boolean node70(double[] feature){
        if(feature[17] < 134.984){
            return node108(feature);
        } else if( feature[17] >= 134.984){
            return node109(feature);
        } else {
            return true;
        }
    }



    private static boolean node71(double[] feature){
        if(feature[0] < 109.843){
            return true;
        } else if( feature[0] >= 109.843){
            return false;
        } else {
            return false;
        }
    }



    private static boolean node75(double[] feature){
        if(feature[15] < 320.987){
            return false;
        } else if( feature[15] >= 320.987){
            return true;
        } else {
            return false;
        }
    }


    private static boolean node76(double[] feature){
        if(feature[1] < 101.942){
            return false;
        } else if( feature[1] >= 101.942){
            return true;
        } else {
            return false;
        }
    }


    private static boolean node80(double[] feature){
        if(feature[18] < 202.585){
            return true;
        } else if( feature[18] >= 202.585){
            return false;
        } else {
            return true;
        }
    }


    private static boolean node84(double[] feature){
        if(feature[18] < 115.263){
            return node118(feature);
        } else if( feature[18] >= 115.263){
            return node119(feature);
        } else {
            return false;
        }
    }


    private static boolean node85(double[] feature){
        if(feature[14] < 178.401){
            return true;
        } else if( feature[14] >= 178.401){
            return false;
        } else {
            return true;
        }
    }


    private static boolean node91(double[] feature){
        if(feature[11] < 215.249){
            return true;
        } else if( feature[11] >= 215.249){
            return false;
        } else {
            return true;
        }
    }

    private static boolean node93(double[] feature){
        if(feature[2] < 113.675){
            return true;
        } else if( feature[2] >= 113.675){
            return node125(feature);
        } else {
            return true;
        }
    }

    private static boolean node94(double[] feature){
        if(feature[11] < 191.924){
            return  true;
        } else if( feature[11] >= 191.924){
            return false;
        } else {
            return false;
        }
    }

    private static boolean node95(double[] feature){
        if(feature[0] < 128.892){
            return true;
        } else if( feature[0] >= 128.892){
            return false;
        } else {
            return true;
        }
    }

    private static boolean node96(double[] feature){
        if(feature[11] < 169.661){
            return false;
        } else if( feature[11] >= 169.661){
            return true;
        } else {
            return true;
        }
    }

    private static boolean node99(double[] feature){
        if(feature[10] < 171.115){
            return true;
        } else if( feature[10] >= 171.115){
            return false;
        } else {
            return false;
        }
    }

    private static boolean node104(double[] feature){
        if(feature[19] < 313.332){
            return node134(feature);
        } else if( feature[19] >= 313.332){
            return true;
        } else {
            return true;
        }
    }

    private static boolean node105(double[] feature){
        if(feature[16] < 21.2809){
            return node136(feature);
        } else if( feature[16] >= 21.2809){
            return node137(feature);
        } else {
            return true;
        }
    }

    private static boolean node106(double[] feature){
        if(feature[0] < 122.873){
            return true;
        } else if( feature[0] >= 122.873){
            return false;
        } else {
            return true;
        }
    }
    private static boolean node107(double[] feature){
        if(feature[0] < 137.021){
            return true;
        } else if( feature[0] >= 137.021){
            return false;
        } else {
            return true;
        }
    }

    private static boolean node108(double[] feature){
        if(feature[1] < 75.431){
            return true;
        } else if( feature[1] >= 75.431){
            return false;
        } else {
            return true;
        }
    }
    private static boolean node109(double[] feature){
        if(feature[18] < 221.788){
            return node144(feature);
        } else if( feature[18] >= 221.788){
            return false;
        } else {
            return true;
        }
    }


    private static boolean node118(double[] feature){
        if(feature[21] < 207.726){
            return node146(feature);
        } else if( feature[21] >= 207.726){
            return node147(feature);
        } else {
            return false;
        }
    }


    private static boolean node119(double[] feature){
        if(feature[19] < 282.182){
            return node148(feature);
        } else if( feature[19] >= 282.182){
            return node149(feature);
        } else {
            return false;
        }
    }


    private static boolean node125(double[] feature){
        if(feature[19] < 335.023){
            return true;
        } else if( feature[19] >= 335.023){
            return node151(feature);
        } else {
            return false;
        }
    }


    private static boolean node134(double[] feature){
        if(feature[0] < 113.279){
            return true;
        } else if( feature[0] >= 113.279){
            return false;
        } else {
            return false;
        }
    }

    private static boolean node136(double[] feature){
        if(feature[3] < 139.285){
            return true;
        } else if( feature[3] >= 139.285){
            return false;
        } else {
            return true;
        }
    }

    private static boolean node137(double[] feature){
        if(feature[19] < 298.683){
            return node156(feature);
        } else if( feature[19] >= 298.683){
            return node157(feature);
        } else {
            return true;
        }
    }

    private static boolean node144(double[] feature){
        if(feature[12] < 49.2571){
            return false;
        } else if( feature[12] >= 49.2571){
            return node159(feature);
        } else {
            return true;
        }
    }

    private static boolean node146(double[] feature){
        if(feature[15] < 279.239){
            return false;
        } else if( feature[15] >= 279.239){
            return node161(feature);
        } else {
            return true;
        }
    }

    private static boolean node147(double[] feature){
        if(feature[15] < 329.064){
            return node162(feature);
        } else if( feature[15] >= 329.064){
            return true;
        } else {
            return false;
        }
    }

    private static boolean node148(double[] feature){
        if(feature[6] < 122.572){
            return node164(feature);
        } else if( feature[6] >= 122.572){
            return false;
        } else {
            return false;
        }
    }


    private static boolean node149(double[] feature){
        if(feature[14] < 179.024){
            return true;
        } else if( feature[14] >= 179.024){
            return false;
        } else {
            return false;
        }
    }


    private static boolean node151(double[] feature){
        if(feature[4] < 109.192){
            return false;
        } else if( feature[4] >= 109.192){
            return true;
        } else {
            return false;
        }
    }


    private static boolean node156(double[] feature){
        if(feature[17] < 239.679){
            return true;
        } else if( feature[17] >= 239.679){
            return node171(feature);
        } else {
            return true;
        }
    }

    private static boolean node157(double[] feature){
        if(feature[12] < 79.6428){
            return node172(feature);
        } else if( feature[12] >= 79.6428){
            return true;
        } else {
            return true;
        }
    }


    private static boolean node159(double[] feature){
        if(feature[20] < 12.5888){
            return false;
        } else if( feature[20] >= 12.5888){
            return true;
        } else {
            return true;
        }
    }

    private static boolean node161(double[] feature){
        if(feature[12] < 52.4585){
            return false;
        } else if( feature[12] >= 52.4585){
            return true;
        } else {
            return true;
        }
    }

    private static boolean node162(double[] feature){
        if(feature[12] < 65.4442){
            return false;
        } else if( feature[12] >= 65.4442){
            return true;
        } else {
            return false;
        }
    }

    private static boolean node164(double[] feature){
        if(feature[8] < 96.1675){
            return false;
        } else if( feature[8] >= 96.1675){
            return true;
        } else {
            return false;
        }
    }

    private static boolean node171(double[] feature){
        if(feature[12] < 86.6145){
            return false;
        } else if( feature[12] >= 86.6145){
            return true;
        } else {
            return false;
        }
    }

    private static boolean node172(double[] feature){
        if(feature[5] < 129.762){
            return false;
        } else if( feature[5] >= 129.762){
            return true;
        } else {
            return true;
        }
    }


}
