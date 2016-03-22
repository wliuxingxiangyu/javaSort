package classOne;

import java.util.LinkedList;
import java.util.Queue;

public class DogAndCatQueue {
	public static class Pet {
		private String type;//数据成员

		public Pet(String type){//宠物类构造函数
			this.type = type;
		}

		public String getPetType(){//获得数据成员
			return this.type;
		}
	}

	public static class Dog extends Pet{
		public Dog() {
			super("dog");
		}
	}

	public static class Cat extends Pet {
		public Cat() {
			super("cat");
		}
	}

	public static class PetEnterQueue{
		//static内部类
		private Pet pet;
		private long count;

		public PetEnterQueue(Pet pet, long count){//宠物进队列类 构造函数
			this.pet = pet;
			this.count = count;
		}

		public Pet getPet() {
			return this.pet;
		}

		public long getCount() {
			return this.count;
		}

		public String getEnterPetType() {
			return this.pet.getPetType();
		}
	}

	public static class DogCatQueue {
		private Queue<PetEnterQueue> dogQ;//定义一个dogQ队列,放PetEnterQueue对象
		private Queue<PetEnterQueue> catQ;
		private long count;//通过计数，count小则早入队列

		public DogCatQueue() {
			this.dogQ = new LinkedList<PetEnterQueue>();//用链表实现dogQ队列，
			this.catQ = new LinkedList<PetEnterQueue>();
			this.count = 0;
		}

		public void add(Pet pet) {
			if (pet.getPetType().equals("dog")) {
				this.dogQ.add(new PetEnterQueue(pet, this.count++));//没有新对象?
			} else if (pet.getPetType().equals("cat")) {
				this.catQ.add(new PetEnterQueue(pet, this.count++));
			} else {
				throw new RuntimeException("err, not dog or cat");
			}
		}

		public Pet pollAll() {
			if (!this.dogQ.isEmpty() && !this.catQ.isEmpty()) {
				if (this.dogQ.peek().getCount() < this.catQ.peek().getCount()) {//peek获取但不弹出
					return this.dogQ.poll().getPet();//狗的序号大，狗在上，弹出狗
				} else {
					return this.catQ.poll().getPet();
				}
			} else if (!this.dogQ.isEmpty()) {//狗队列非空
				return this.dogQ.poll().getPet();
			} else if (!this.catQ.isEmpty()) {//猫队列非空
				return this.catQ.poll().getPet();
			} else {
				throw new RuntimeException("err, queue is empty!");
			}
		}

		public Dog pollDog() {
			if (!this.isDogQueueEmpty()) {//狗队列非空
				return (Dog) this.dogQ.poll().getPet();//poll()移除和返回队列的头
			} else {
				throw new RuntimeException("Dog queue is empty!");
			}
		}

		public Cat pollCat() {
			if (!this.isCatQueueEmpty()) {//猫队列非空
				return (Cat) this.catQ.poll().getPet();
			} else
				throw new RuntimeException("Cat queue is empty!");
		}

		public boolean isEmpty() {//狗和猫 队列都空
			return this.dogQ.isEmpty() && this.catQ.isEmpty();
		}

		public boolean isDogQueueEmpty() {
			return this.dogQ.isEmpty();
		}

		public boolean isCatQueueEmpty() {
			return this.catQ.isEmpty();
		}

	}

	public static void main(String[] args) {
		DogCatQueue test = new DogCatQueue();

		Pet dog1 = new Dog();
		Pet cat1 = new Cat();
		Pet dog2 = new Dog();
		Pet cat2 = new Cat();
		Pet dog3 = new Dog();
		Pet cat3 = new Cat();

		test.add(dog1);
		test.add(cat1);
		test.add(dog2);
		test.add(cat2);
		test.add(dog3);
		test.add(cat3);

		test.add(dog1);
		test.add(cat1);
		test.add(dog2);
		test.add(cat2);
		test.add(dog3);
		test.add(cat3);

		test.add(dog1);
		test.add(cat1);
		test.add(dog2);
		test.add(cat2);
		test.add(dog3);
		test.add(cat3);
		while (!test.isDogQueueEmpty()) {
			System.out.println(test.pollDog().getPetType());
		}
		while (!test.isEmpty()) {
			System.out.println(test.pollAll().getPetType());
		}
	}

}
