use linkedlist::SinglyLinkedList;

pub mod linkedlist;

fn main() {
    let mut list = SinglyLinkedList::new();

    list.push(32);    
    list.push(33);
    list.push(1);

    list.pop();

    println!("list : {}", list);
}
