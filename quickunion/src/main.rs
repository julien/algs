fn main() {
    let mut qu = QuickUnion::new(10);
    println!("{:?}\n", qu);

    println!("Connecting 1 and 2");
    qu.union(1, 2);

    println!("{:?}\n", qu);

    println!("Are 1 and 2 connected? {}\n", qu.connected(1, 2));

    println!("Connecting 0 and 9");
    qu.union(0, 9);
    println!("{:?}\n", qu);

    println!("Connecting 8 and 5");
    qu.union(8, 5);
    println!("{:?}\n", qu);

    println!("Connecting 8 and 5");
    qu.union(5, 2);
    println!("{:?}\n", qu);

    println!("qu.root(8): {:?}\n", qu.root(8));

    println!("Connecting 3 and 5");
    qu.union(3, 5);
    println!("{:?}\n", qu);

    println!("Connecting 3 and 0");
    qu.union(3, 0);
    println!("{:?}\n", qu);
}

#[derive(Debug)]
struct QuickUnion {
    id: Vec<u32>,
}

impl QuickUnion {
    fn new(n: u32) -> QuickUnion {
        let id = (0..n).collect();
        QuickUnion { id: id }
    }

    fn root(&self, mut i: u32) -> u32 {
        while i != self.id[i as usize] {
            i = self.id[i as usize];
        }
        i
    }

    fn connected(&self, p: u32, q: u32) -> bool {
        self.root(p) == self.root(q)
    }

    fn union(&mut self, p: u32, q: u32) {
        let i: u32 = self.root(p);
        let j: u32 = self.root(q);

        self.id[i as usize] = j;
    }
}
