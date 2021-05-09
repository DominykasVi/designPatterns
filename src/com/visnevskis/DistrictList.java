package com.visnevskis;

import java.util.Arrays;

public class DistrictList extends Throwable implements DistrictContainer{
    District[] districtArray = new District[10];
    int size = 10;
    int fill = 0;


    public void addDistrict(District newDistrict){
        if(fill >= size){
            size += 10;
            districtArray = Arrays.copyOf(districtArray, size);
        }
        districtArray[fill] = newDistrict;
        fill++;
    }

    public void removeDistrict(int position){
        for (int i = position; i < districtArray.length - 1; i++) {
            districtArray[i] = districtArray[i + 1];
        }
        fill--;
        if(fill < size-10 && fill != 0){
            size -= 10;
            districtArray = Arrays.copyOf(districtArray, size);
        }
    }

    public District getDistrict(int index) throws ArrayIndexOutOfBoundsException{
        if(index < 0 || index > fill){
            throw new ArrayIndexOutOfBoundsException("Index out of bounds");
        }
        return districtArray[index];
    }

    public int getDistrictSize() {
        return fill;
    }

    @Override
    public Iterator getIterator() {
        return new IterateThroughDistricts();
    }

    private class IterateThroughDistricts implements Iterator{
        int i;
        @Override
        public boolean hasNext() {
            if(i < fill){
                return true;
            }
            return false;
        }

        @Override
        public District next() {
            if(this.hasNext()){
                return districtArray[i++];
            }
            return null;
        }
    }
}
